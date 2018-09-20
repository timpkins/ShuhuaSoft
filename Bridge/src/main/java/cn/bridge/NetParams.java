package cn.bridge;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 网络请求参数
 * @author timpkins
 */
public class NetParams {
    private ConcurrentHashMap<String, String> urlParams;
    private ConcurrentHashMap<String, FileWrapper> fileParams;

    public NetParams() {
        urlParams = new ConcurrentHashMap<>();
        fileParams = new ConcurrentHashMap<>();
    }

    public NetParams(@NonNull String key, @NonNull String value) {
        this();
        put(key, value);
    }

    public NetParams(@NonNull Object... keysAndValues) {
        this();
        int len = keysAndValues.length;
        if (len % 2 != 0) {
            throw new IllegalArgumentException("Supplied arguments must be even");
        }
        for (int i = 0; i < len; i += 2) {
            put(String.valueOf(keysAndValues[i]), String.valueOf(keysAndValues[i + 1]));
        }
    }

    public void put(@NonNull String key, @NonNull String value) {
        urlParams.put(key, value);
    }

    public void put(@NonNull String key, @NonNull File file) {
        try {
            put(key, new FileInputStream(file), file.getName());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file " + file.getAbsolutePath() + " can not found");
        }
    }

    public void put(@NonNull String key, @NonNull InputStream stream) {
        put(key, stream, "NoName");
    }

    public void put(@NonNull String key, @NonNull InputStream stream, @NonNull String fileName) {
        put(key, stream, fileName, null);
    }

    public void put(@NonNull String key, @NonNull InputStream stream, @NonNull String fileName, @Nullable String contentType) {
        fileParams.put(key, new FileWrapper(stream, fileName, contentType));
    }

    public void remove(@NonNull String key) {
        urlParams.remove(key);
        fileParams.remove(key);
    }

    /**
     * 生成GET请求参数拼接
     * @return GET参数字符串
     */
    public String toGetString(){
        StringBuilder builder = new StringBuilder("&");
        for (Entry<String, String> entry : urlParams.entrySet()){
            builder.append(entry.getKey().trim());
            builder.append("=");
            builder.append(entry.getValue().trim());
        }
        return builder.toString();
    }

    /**
     * 生成POST请求参数JSON格式
     * @return POST请求参数JSON格式
     */
    public String toJsonString(){
        return new Gson().toJson(urlParams);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (ConcurrentHashMap.Entry<String, String> entry : urlParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append(entry.getValue());
        }

        for (ConcurrentHashMap.Entry<String, FileWrapper> entry : fileParams.entrySet()) {
            if (result.length() > 0)
                result.append("&");

            result.append(entry.getKey());
            result.append("=");
            result.append("FILE");
        }

        return result.toString();
    }

    public ConcurrentHashMap<String, String> getUrlParams() {
        return urlParams;
    }

    private static class FileWrapper {
        public InputStream inputStream;
        public String fileName;
        public String contentType;

        public FileWrapper(InputStream inputStream, String fileName, String contentType) {
            this.inputStream = inputStream;
            this.fileName = fileName;
            this.contentType = contentType;
        }

        public String getFileName() {
            return fileName != null ? fileName : "NoName";
        }
    }
}
