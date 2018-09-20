package com.mec.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.mec.Constants.NetUrl;
import com.mec.Constants.Preference;
import com.mec.NetHttpCallback;
import com.mec.view.CircularImageView;
import com.shuhuasoft.R;

import java.io.File;
import java.util.ArrayList;

import cn.base.util.LogUtils;
import cn.bridge.NetParams;
import cn.bridge.NetRequester;
import cn.bridge.RequestOption;
import cn.picker.activity.PhotoSelectorActivity;


/**
 * @author timpkins
 */
public class MineInfoActivity extends MecTitleActivity implements OnClickListener {
    private ArrayList<String> result = new ArrayList<>();
    private CircularImageView ivAvatar;

    @Override
    protected int setContentLayout() {
        return R.layout.activity_mine_info;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        setTitleContent(R.string.info_title);
    }

    @Override
    protected void initViews() {
        LinearLayout llAvatar = findView(R.id.llAvatar);
        ivAvatar = findView(R.id.ivAvatar);

        llAvatar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llAvatar:
                // 最大可选照片数
//                PhotoSelectorSetting.MAX_PHOTO_SUM = 1;
                // 照片列表列数
//                PhotoSelectorSetting.COLUMN_COUNT = 4;
                Intent intent = new Intent(MineInfoActivity.this, PhotoSelectorActivity.class);
                startActivityForResult(intent, 0x01);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0x01:
                if (resultCode == RESULT_OK) {
                    // result为照片绝对路径集合,isSelectedFullImage标识是否选择原图
                    result = data.getStringArrayListExtra(PhotoSelectorActivity.LAST_MODIFIED_LIST);
                    // 获取照片后的操作
                    ivAvatar.setImageBitmap(BitmapFactory.decodeFile(result.get(0)));

                    uploadAvatar(result.get(0));
                }
                break;
        }
    }

    private void uploadAvatar(String path){
        NetParams params = new NetParams();
        params.put("token", application().getPreferencesHelper().getData(Preference.TOKE, ""));
        params.put("file", new File(path));
        RequestOption option = new RequestOption();
        option.setMediaType(RequestOption.MEDIA_FORM);
        new NetRequester(this, option).post(NetUrl.AVATAR, params, new NetHttpCallback() {
            @Override
            public void onSuccess(String result) {
                LogUtils.e("TAG", result);
            }
        });
    }
}
