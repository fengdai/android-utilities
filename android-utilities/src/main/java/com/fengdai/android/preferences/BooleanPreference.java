package com.fengdai.android.preferences;

import android.content.SharedPreferences;

/**
 * @author daifeng
 */
public class BooleanPreference extends AbstractPreference<Boolean> {
    public BooleanPreference(SharedPreferences sharedPreferences, String key) {
        super(sharedPreferences, key);
    }

    @Override
    protected Boolean doGet() {
        return sharedPreferences.getBoolean(key, false);
    }

    @Override
    public void set(Boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }
}
