package com.fengdai.android.json;

import java.lang.reflect.Type;

/**
 * Json Converter.
 *
 * @author daifeng
 */
public interface JsonConverter {
    /**
     * Convert Object to Json string.
     */
    String toJson(Object object);

    /**
     * Convert Json string to Object.
     */
    <T> T fromJson(String jsonString, Type type);
}
