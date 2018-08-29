package cn.picker.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.picker.R;
import cn.picker.adapter.FolderListAdapter;
import cn.picker.adapter.PhotoListAdapter;
import cn.picker.bean.ImageFolderBean;
import cn.picker.utils.ScreenUtil;

/**
 * Created by Fire on 2017/4/8.
 */

public class PhotoSelectorActivity extends AppCompatActivity implements OnClickListener {
    private static final String TAG = "PhotoSelectorActivity";
    public static final String LAST_MODIFIED_LIST = "last_modified_list";
    private static final int REQUEST_PREVIEW_PHOTO = 100;
    /**
     * 保存相册目录名和相册所有照片路径
     */
    private HashMap<String, List<String>> photoGroupMap = new HashMap<>();
    /**
     * 保存相册目录名
     */
    private List<ImageFolderBean> photoFolders = new ArrayList<>();
    /**
     * 照片列表
     */
    private PhotoListAdapter photoListAdapter;
    /**
     * 目录列表
     */
    private FolderListAdapter folderListAdapter;
    private TextView tvCancel;
    private TextView tvAlbumName;
    private ImageView ivAlbumArrow;
    private RecyclerView rvFolderList;
    private View vAlpha;
    private List<String> chileList;
    private List<String> value;
    private List<String> photoFolder;
    private ArrayList<String> photoList = new ArrayList<>();
    private RecyclerView rvPhotoList;
    public static int COLUMN_COUNT = 4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_selector);
        getImages();
        rvPhotoList = findViewById(R.id.rv_photo_list);
        tvCancel = findViewById(R.id.tv_select_cancel);
        tvAlbumName = findViewById(R.id.tv_album_name);
        ivAlbumArrow = findViewById(R.id.iv_album_arrow);
        rvFolderList = findViewById(R.id.rv_folder_list);
        vAlpha = findViewById(R.id.v_alpha);
        tvCancel.setOnClickListener(this);
        tvAlbumName.setOnClickListener(this);
        ivAlbumArrow.setOnClickListener(this);
        vAlpha.setOnClickListener(this);
        photoListAdapter = new PhotoListAdapter(this, photoGroupMap.get("全部照片"));
        if (COLUMN_COUNT <= 1) {
            rvPhotoList.setLayoutManager(new LinearLayoutManager(this));
        } else {
            rvPhotoList.setLayoutManager(new GridLayoutManager(this, COLUMN_COUNT));
        }
        rvPhotoList.setAdapter(photoListAdapter);
        photoListAdapter.setOnRecyclerViewItemClickListener(new OnPhotoListClick());
        rvFolderList.setLayoutManager(new LinearLayoutManager(this));
        ViewGroup.LayoutParams lp = rvFolderList.getLayoutParams();
        lp.height = (int) (ScreenUtil.getScreenHeight(this) * 0.618);
        rvFolderList.setLayoutParams(lp);
        folderListAdapter = new FolderListAdapter(this, photoFolders);
        folderListAdapter.setOnRecyclerViewItemClickListener(new OnFolderListClick());
        rvFolderList.setAdapter(folderListAdapter);
    }

    /**
     * 扫描手机中所有图片
     */
    private void getImages() {
        Uri imageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC";
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(imageUri, null, null, null, sortOrder);
        if (cursor == null) {
            return;
        }
        photoFolder = new ArrayList<>();
        photoGroupMap.put(getString(R.string.all_photos), photoFolder);
        while (cursor.moveToNext()) {
            //获取图片的路径
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            photoGroupMap.get(getString(R.string.all_photos)).add(path);
            //获取该图片的父路径名
            String parentName = new File(path).getParentFile().getName();
            //根据父路径名将图片放入到mGroupMap中
            if (photoGroupMap.containsKey(parentName)) {
                photoGroupMap.get(parentName).add(path);
            } else {
                chileList = new ArrayList<>();
                chileList.add(path);
                photoGroupMap.put(parentName, chileList);
            }
        }
        //扫描图片完成
        cursor.close();
        photoFolders.addAll(subGroupOfImage(photoGroupMap));
    }

    private List<ImageFolderBean> subGroupOfImage(HashMap<String, List<String>> mGroupMap) {
        List<ImageFolderBean> list = new ArrayList<>();
        ImageFolderBean imageFolderBean;
        for (Map.Entry<String, List<String>> entry : mGroupMap.entrySet()) {
            imageFolderBean = new ImageFolderBean();
            String key = entry.getKey();
            if (key.equals(getString(R.string.all_photos))) {
                imageFolderBean.setSelected(true);
            } else {
                imageFolderBean.setSelected(false);
            }
            value = entry.getValue();
            imageFolderBean.setFolderName(key);
            imageFolderBean.setImageCounts(value.size());
            imageFolderBean.setImagePaths(value);
            if (key.equals(getString(R.string.all_photos))) {
                list.add(0, imageFolderBean);
            } else {
                list.add(imageFolderBean);
            }
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        if (v == tvCancel) {// 取消
            setResult(RESULT_CANCELED);
            finish();
        } else if (v == tvAlbumName) {// 选择相册
            toggleFolderList();
        } else if (v == vAlpha) {// 点击相册列表外部
            toggleFolderList();
        }
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            PhotoSelectorSetting.SCREEN_RATIO = (float) vAlpha.getWidth() / vAlpha.getHeight();
//        }
//    }

    private class OnFolderListClick implements FolderListAdapter.OnRecyclerViewItemClickListener {

        @Override
        public void onRecyclerViewItemClick(View v, int position) {
            toggleFolderSelected(position);
            photoFolder = photoGroupMap.get(photoFolders.get(position).getFolderName());
            photoListAdapter.setData(photoFolder);
            folderListAdapter.notifyDataSetChanged();
            tvAlbumName.setText(photoFolders.get(position).getFolderName());
            toggleFolderList();
            rvPhotoList.smoothScrollToPosition(0);
        }
    }

    private class OnPhotoListClick implements PhotoListAdapter.OnRecyclerViewItemClickListener {

        @Override
        public void onRecyclerViewItemClick(View v, int position) {
            if (position == 0) {
                showCameraAction();
            } else {
//                if (v.getId() == R.id.iv_photo_checked) {
//                    boolean photoSelected = PhotoMessage.togglePhotoSelected(photoFolder.get(position));
//                    if (photoSelected) {
////                        changeOKButtonStatus();
//                    } else {
//                        String string = getString(R.string.photo_sum_max);
//                        String format = String.format(string, MAX_PHOTO_SUM);
//                        Toast.makeText(PhotoSelectorActivity.this, format, Toast.LENGTH_SHORT).show();
//                    }
//                    photoListAdapter.notifyDataSetChanged();
//                } else {
//                    Intent intent = new Intent(PhotoSelectorActivity.this, PhotoViewActivity.class);
//                    photoList = new ArrayList<>();
//                    photoList.addAll(photoFolder);
//                    intent.putExtra("PhotoList", photoList);
//                    intent.putExtra("Index", position);
//                    startActivityForResult(intent, REQUEST_PREVIEW_PHOTO);

//                ArrayList<String> image = new ArrayList<>();
//                image.add(tmpFile.getAbsolutePath());

                photoList.add(photoFolder.get(position));
                Intent intent = new Intent();
                intent.putExtra(LAST_MODIFIED_LIST, photoList);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    /**
     * 调用系统相机
     */
    private void showCameraAction() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(this.getPackageManager()) != null) {
            File tmpFile = createTmpFile(this);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tmpFile));
            startActivityForResult(cameraIntent, 0x02);
        } else {
            Toast.makeText(this, "无法调用系统相机", Toast.LENGTH_SHORT).show();
        }
    }

    public static File createTmpFile(Context context) {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 已挂载
            File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM + "/Camera/");

            String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss", Locale.CHINA).format(new Date());
            String fileName = timeStamp + "";
            tmpFile = new File(pic, fileName + ".jpg");
            return tmpFile;
        } else {
            File cacheDir = context.getCacheDir();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            String fileName = "multi_image_" + timeStamp + "";
            File tmpFile = new File(cacheDir, fileName + ".jpg");
            return tmpFile;
        }
    }

    private static File tmpFile;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_PREVIEW_PHOTO:
                if (resultCode == RESULT_OK) {
                    photoList = new ArrayList<>();
//                    photoList.addAll(SELECTED_PHOTOS);
                    Intent intent = new Intent();
                    intent.putExtra(LAST_MODIFIED_LIST, photoList);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                photoListAdapter.notifyDataSetChanged();
//                changeOKButtonStatus();
                break;
        }

        // 相机拍照完成后，返回图片路径
        if (requestCode == 0x02) {
            if (resultCode == Activity.RESULT_OK) {
                if (tmpFile != null) {
                    ArrayList<String> image = new ArrayList<>();
                    image.add(tmpFile.getAbsolutePath());
                    Intent intent = new Intent();
                    intent.putExtra(LAST_MODIFIED_LIST, image);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            } else {
                if (tmpFile != null && tmpFile.exists()) {
                    tmpFile.delete();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (rvFolderList.isShown()) {
            toggleFolderList();
        } else {
            super.onBackPressed();
        }
    }

    private void toggleFolderList() {
        Animation animation;
        if (rvFolderList.isShown()) {
            rvFolderList.setVisibility(View.GONE);
            vAlpha.setVisibility(View.INVISIBLE);
            ivAlbumArrow.setImageResource(R.mipmap.ic_arrow_down);
            animation = AnimationUtils.loadAnimation(this, R.anim.popup_hidden_anim);
        } else {
            rvFolderList.setVisibility(View.VISIBLE);
            vAlpha.setVisibility(View.VISIBLE);
            ivAlbumArrow.setImageResource(R.mipmap.ic_arrow_up);
            animation = AnimationUtils.loadAnimation(this, R.anim.popup_show_anim);
        }
        rvFolderList.setAnimation(animation);
        folderListAdapter.notifyDataSetChanged();
    }

    private void toggleFolderSelected(int position) {
        for (ImageFolderBean photoFolder : photoFolders) {
            photoFolder.setSelected(false);
        }
        photoFolders.get(position).setSelected(true);
    }
}
