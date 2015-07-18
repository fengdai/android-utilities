package com.fengdai.android.preferences;

/**
 * @author daifeng
 */
public interface Preference<T> {

    void set(T value);

    T get();

    T get(T defValue);
}
