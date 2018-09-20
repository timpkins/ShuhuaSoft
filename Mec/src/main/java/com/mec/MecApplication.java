package com.mec;

import com.mec.Constants.Preference;

import cn.base.BaseApplication;
import cn.base.util.SharedPreferencesHelper;

/**
 * @author timpkins
 */
public class MecApplication extends BaseApplication {
    private SharedPreferencesHelper preferencesHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        preferencesHelper = SharedPreferencesHelper.newInstance(this, Preference.PREFERENCES_HOME);
    }

    public SharedPreferencesHelper getPreferencesHelper() {
        return preferencesHelper;
    }
}
