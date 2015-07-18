package com.fengdai.android.preferences;

import android.content.SharedPreferences;

import com.fengdai.android.json.JsonConverter;

/**
 * @author daifeng
 */
public class JsonPreference<T> extends AbstractPreference<T> {
    private T cache;
    private JsonConverter json;
    private Class<T> clazz;

    public JsonPreference(SharedPreferences sharedPreferences, String key, JsonConverter json,
                          Class<T> clazz) {
        super(sharedPreferences, key);
        this.clazz = clazz;
        this.json = json;
    }

    @Override
    protected T doGet() {
        if (this.cache != null) {
            return cache;
        }
        return json.fromJson(sharedPreferences.getString(key, null), clazz);
    }

    @Override
    public T get(T defValue) {
        if (this.cache != null) {
            return cache;
        }
        return super.get(defValue);
    }

    @Override
    public void set(T value) {
        this.cache = value;
        this.sharedPreferences.edit().putString(key, json.toJson(value)).apply();
    }
}
