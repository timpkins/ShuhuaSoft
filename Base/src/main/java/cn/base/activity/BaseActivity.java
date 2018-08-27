package cn.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * @author timpkins
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentLayout());

        initViews();
    }

    @LayoutRes
    protected abstract int setContentLayout();

    protected abstract void initViews();

    protected <T extends View> T findView(@IdRes int idRes) {
        return findViewById(idRes);
    }

    protected <T extends AppCompatActivity> void startActivity(Class<T> clazz) {
        startActivity(new Intent(this, clazz));
    }
}
