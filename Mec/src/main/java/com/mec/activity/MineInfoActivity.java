package com.mec.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.mec.view.CircularImageView;
import com.shuhuasoft.R;

import java.util.ArrayList;

import cn.picker.activity.PhotoSelectorActivity;
import cn.picker.models.PhotoSelectorSetting;


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
                PhotoSelectorSetting.MAX_PHOTO_SUM = 1;
                // 照片列表列数
                PhotoSelectorSetting.COLUMN_COUNT = 4;
                Intent intent = new Intent(MineInfoActivity.this, PhotoSelectorActivity.class);
                intent.putExtra(PhotoSelectorSetting.LAST_MODIFIED_LIST, result);
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
                    result = data.getStringArrayListExtra(PhotoSelectorSetting.LAST_MODIFIED_LIST);
                    // 获取照片后的操作
                    ivAvatar.setImageBitmap(BitmapFactory.decodeFile(result.get(0)));
                }
                break;
        }
    }
}
