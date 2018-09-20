package cn.bridge;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import cn.bridge.fragment.LoadingDialogFragment;


/**
 * @author timpkins
 */
public class LoadingDialogUtils {
    private static LoadingDialogUtils activityLoadingUtils;
    private static LoadingDialogFragment loadingDialog;
    private AppCompatActivity activity;

    private LoadingDialogUtils(AppCompatActivity activity) {
        this.activity = activity;
    }

    public static LoadingDialogUtils getInstance(AppCompatActivity activity) {
        loadingDialog = new LoadingDialogFragment();
        if (activityLoadingUtils == null) {
            activityLoadingUtils = new LoadingDialogUtils(activity);
        }
        return activityLoadingUtils;
    }

    public void onShow() {
        Log.e("TAG", "对话框显示");
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        if (loadingDialog.isAdded()) {
            Log.e("TAG", "对话框显示   已添加");
            transaction.show(loadingDialog).commitAllowingStateLoss();
        } else {
            Log.e("TAG", "对话框显示   未添加");
            transaction.add(loadingDialog, "LOAD").show(loadingDialog).commitAllowingStateLoss();
        }
    }

    public void onDismiss() {
        Log.e("TAG", "对话框消失");
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.hide(loadingDialog).commitAllowingStateLoss();
    }
}
