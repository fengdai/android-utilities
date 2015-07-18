package com.fengdai.android.preferences;

import android.content.SharedPreferences;

/**
 * @author daifeng
 */
public abstract class AbstractPreference<T> implements Preference<T> {
    protected final String key;
    protected final SharedPreferences sharedPreferences;

    public AbstractPreference(SharedPreferences sharedPreferences, String key) {
        this.sharedPreferences = sharedPreferences;
        this.key = key;
    }

    protected abstract T doGet();

    @Override
    public T get() {
        return this.doGet();
    }

    @Override
    public T get(T defValue) {
        if (!sharedPreferences.contains(key)) {
            return defValue;
        }
        return this.doGet();
    }
}
