package cn.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author timpkins
 */
public abstract class BaseTitleActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initTitle();
    }

    protected abstract void initTitle();
}
