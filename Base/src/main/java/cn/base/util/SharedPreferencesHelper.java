/*
 * Copyright (C) 2018 timpkins (timpkins@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.base.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.Set;

/**
 * SharedPrerences数据保存帮助类
 * @author timpkins
 */
public class SharedPreferencesHelper {
    private static SharedPreferencesHelper helper;
    private static final String DEFAULT_NAME = "preferences_helper";
    private Editor editor;
    private SharedPreferences preferences;

    @SuppressLint("CommitPrefEdits")
    private SharedPreferencesHelper(Context context, String name) {
        if (TextUtils.isEmpty(name)){
            name = DEFAULT_NAME;
        }
        preferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public static SharedPreferencesHelper newInstance(Context context, String name) {
        if (helper == null) {
            helper = new SharedPreferencesHelper(context, name);
        }
        return helper;
    }

    /**
     * 将数据保存到SharedPreferences中
     * @param key 数据在SharedPreferences中对应的Key
     * @param value 待保存的数据
     */
    public void putData(@NonNull String key, @NonNull String value) {
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * 将数据保存到SharedPreferences中
     * @param key 数据在SharedPreferences中对应的Key
     * @param values 待保存的数据
     */
    public void putData(@NonNull String key, @NonNull Set<String> values) {
        editor.putStringSet(key, values);
        editor.apply();
    }

    /**
     * 将数据保存到SharedPreferences中
     * @param key 数据在SharedPreferences中对应的Key
     * @param value 待保存的数据
     */
    public void putData(@NonNull String key, int value) {
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * 将数据保存到SharedPreferences中
     * @param key 数据在SharedPreferences中对应的Key
     * @param value 待保存的数据
     */
    public void putData(@NonNull String key, long value) {
        editor.putLong(key, value);
        editor.apply();
    }

    /**
     * 将数据保存到SharedPreferences中
     * @param key 数据在SharedPreferences中对应的Key
     * @param value 待保存的数据
     */
    public void putData(@NonNull String key, float value) {
        editor.putFloat(key, value);
        editor.apply();
    }

    /**
     * 将数据保存到SharedPreferences中
     * @param key 数据在SharedPreferences中对应的Key
     * @param value 待保存的数据
     */
    public void putData(@NonNull String key, boolean value) {
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * 删除key对应的数据
     * @param key 待删除数据对应的Key
     */
    public void remove(@NonNull String key) {
        editor.remove(key);
        editor.apply();
    }

    /**
     * 清空SharedPrefences中的数据
     */
    public void clear() {
        editor.clear();
        editor.apply();
    }
    //查

    /**
     * 从SharedPrefences中获取对应的值，如果没有则返回默认值
     * @param key 待获取数据对应的Key
     * @param defValue 默认值
     * @return SharePreferences中key对应的值
     */
    public String getData(@NonNull String key, String defValue){
        return preferences.getString(key, defValue);
    }

    /**
     * 从SharedPrefences中获取对应的值，如果没有则返回默认值
     * @param key 待获取数据对应的Key
     * @param defValue 默认值
     * @return SharePreferences中key对应的值
     */
    public Set<String> getData(@NonNull String key, Set<String> defValue){
        return preferences.getStringSet(key, defValue);
    }

    /**
     * 从SharedPrefences中获取对应的值，如果没有则返回默认值
     * @param key 待获取数据对应的Key
     * @param defValue 默认值
     * @return SharePreferences中key对应的值
     */
    public int getData(@NonNull String key, int defValue){
        return preferences.getInt(key, defValue);
    }

    /**
     * 从SharedPrefences中获取对应的值，如果没有则返回默认值
     * @param key 待获取数据对应的Key
     * @param defValue 默认值
     * @return SharePreferences中key对应的值
     */
    public long getData(@NonNull String key, long defValue){
        return preferences.getLong(key, defValue);
    }

    /**
     * 从SharedPrefences中获取对应的值，如果没有则返回默认值
     * @param key 待获取数据对应的Key
     * @param defValue 默认值
     * @return SharePreferences中key对应的值
     */
    public float getData(@NonNull String key, float defValue){
        return preferences.getFloat(key, defValue);
    }

    /**
     * 从SharedPrefences中获取对应的值，如果没有则返回默认值
     * @param key 待获取数据对应的Key
     * @param defValue 默认值
     * @return SharePreferences中key对应的值
     */
    public boolean getData(@NonNull String key, boolean defValue){
        return preferences.getBoolean(key, defValue);
    }
}
