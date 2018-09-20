package cn.bridge;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import cn.bridge.fragment.LoadingDialogFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 发起各种网络请求
 * @author timpkins
 */
public final class NetRequester {
    private static final int GET = 0x01;
    private static final int POST = 0x02;
    private static final int HEAD = 0x03;
    private static final int DELETE = 0x04;
    private static final int PUT = 0x05;

    @IntDef({GET, POST, HEAD, DELETE, PUT})
    @Retention(RetentionPolicy.SOURCE)
    private @interface HttpMethod {

    }

    private RequestOption option;
    private AppCompatActivity activity;
    private OkHttpClient client;
    private LoadingDialogUtils loadingDialogUtils;

    public NetRequester(AppCompatActivity activity) {
        this.activity = activity;
        this.option = new RequestOption();
        loadingDialogUtils = LoadingDialogUtils.getInstance(activity);
        client = new OkHttpClient.Builder().build();
    }

    public NetRequester(AppCompatActivity activity, RequestOption option) {
        this.activity = activity;
        this.option = option;

        OkHttpClient.Builder http = new OkHttpClient.Builder();
        http.connectTimeout(60, TimeUnit.SECONDS);
        http.writeTimeout(60, TimeUnit.SECONDS);
        http.readTimeout(60, TimeUnit.SECONDS);
        client = http.build();
        loadingDialogUtils = LoadingDialogUtils.getInstance(activity);

    }

    public void setOption(@NonNull RequestOption option) {
        this.option = option;
    }

    /**
     * 发送GET请求
     * @param url 请求地址
     * @param callback 请求回调
     */
    public void get(@NonNull String url, @NonNull NetCallback callback) {
        request(createRequest(GET, url), callback);
    }

    /**
     * 发送GET请求
     * @param url 请求地址
     * @param params 请求参数
     * @param callback 请求回调
     */
    public void get(@NonNull String url, @NonNull NetParams params, @NonNull NetCallback callback) {
        request(createRequest(GET, url, params), callback);
    }

    /**
     * 发送POST请求
     * @param url 请求地址
     * @param callback 请求回调
     */
    public void post(@NonNull String url, @NonNull NetCallback callback) {
        request(createRequest(POST, url), callback);
    }

    /**
     * 发送POST请求
     * @param url 请求地址
     * @param params 请求参数
     * @param callback 请求回调
     */
    public void post(@NonNull String url, @NonNull NetParams params, @NonNull NetCallback callback) {
        request(createRequest(POST, url, params), callback);
    }

    public void head(String url, NetCallback callback) {

    }

    public void head(String url, NetParams params, NetCallback callback) {

    }

    public void delete(String url, NetCallback callback) {

    }

    public void delete(String url, NetParams params, NetCallback callback) {

    }

    public void put(@NonNull String url, @NonNull NetCallback callback) {

    }

    public void put(@NonNull String url, @NonNull NetParams params, @NonNull NetCallback callback) {

    }

    public void patch(String url, NetCallback callback) {

    }

    public void patch(String url, NetParams params, NetCallback callback) {

    }

    private Request createRequest(@HttpMethod int method, @NonNull String url) {
        Builder builder = new Builder();
        switch (method) {
            case GET:
                builder.get();
                break;
            case HEAD:
                builder.head();
                break;
            case POST:
                MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
                bodyBuilder.setType(option.getMediaType());
                break;
            case PUT:
                builder.put(RequestBody.create(option.getMediaType(), ""));
                break;
            case DELETE:
                builder.delete();
                break;
        }
        return builder.url(url).build();
    }

    private Request createRequest(@HttpMethod int method, @NonNull String url, @NonNull NetParams params) {
        Builder builder = new Builder();
        switch (method) {
            case GET:
                builder.get();
                url = url.concat(params.toGetString());
                break;
            case HEAD:
                break;
            case POST:
                builder.post(createRequestBody(params));
                break;
            case PUT:
                builder.put(createRequestBody(params));
                break;
            case DELETE:
                break;
        }
        return builder.url(url).build();
    }

    private RequestBody createRequestBody(@NonNull NetParams params) {
        RequestBody body;
        switch (option.getMediaType().toString()) {
            case RequestOption.MEDIA_JSON:
                body = RequestBody.create(option.getMediaType(), params.toJsonString());
                break;
            case RequestOption.MEDIA_FORM:
                MultipartBody.Builder builder = new MultipartBody.Builder();
                builder.setType(MediaType.get(RequestOption.MEDIA_FORM));
                for (Entry<String, String> entry : params.getUrlParams().entrySet()) {
                    builder.addFormDataPart(entry.getKey(), entry.getValue());
                }
                body = builder.build();
                break;
            default:
                body = RequestBody.create(option.getMediaType(), params.toJsonString());
                break;
        }
        return body;
    }

    private void request(@NonNull Request request, @NonNull NetCallback callback) {
        LoadingDialogFragment fragment = new LoadingDialogFragment();
        activity.getSupportFragmentManager().beginTransaction().add(fragment, this.getClass().toString()).commitAllowingStateLoss();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                activity.getSupportFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
                String url = call.request().url().toString();
                activity.runOnUiThread(() -> callback.onError(activity, url, e));
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                activity.getSupportFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
                String url = call.request().url().toString();
                if (response.isSuccessful()) {
                    try {
                        ResponseBody body = response.body();
                        String result = body != null ? body.string() : "";
                        activity.runOnUiThread(() -> callback.onSuccess(activity, url, result));
                    } catch (IOException e) {
                        activity.runOnUiThread(() -> callback.onError(activity, url, e));
                    }
                } else {
                    activity.runOnUiThread(() -> callback.onFailure(activity, url));
                }
            }
        });
    }
}
