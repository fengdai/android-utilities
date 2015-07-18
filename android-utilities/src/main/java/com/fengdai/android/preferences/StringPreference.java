package com.fengdai.android.preferences;

import android.content.SharedPreferences;

/**
 * @author daifeng
 */
public class StringPreference extends AbstractPreference<String> {
    public StringPreference(SharedPreferences sharedPreferences, String key) {
        super(sharedPreferences, key);
    }

    @Override
    protected String doGet() {
        return sharedPreferences.getString(key, null);
    }

    @Override
    public void set(String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }
}
