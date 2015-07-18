package com.fengdai.android.preferences;

import android.content.SharedPreferences;

/**
 * @author daifeng
 */
public class IntegerPreference extends AbstractPreference<Integer> {
    public IntegerPreference(SharedPreferences sharedPreferences, String key) {
        super(sharedPreferences, key);
    }

    @Override
    protected Integer doGet() {
        return sharedPreferences.getInt(key, -1);
    }

    @Override
    public void set(Integer value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }
}
