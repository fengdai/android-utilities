package com.fengdai.android.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {

    /**
     * Remove all null element in the given array. Will return null if the array is null.
     *
     * @return A new array if any element is removed, or the origin array if nothing removed.
     */
    public static <T> T[] removeNull(T... array) {
        if (array == null) {
            return null;
        }
        if (!array.getClass().isArray()) {
            throw new IllegalArgumentException(array.getClass().toString() + " is not Array class!");
        }
        List<T> list = new ArrayList<T>(Arrays.asList(array));
        boolean changed = false;
        for (T data : array) {
            if (data == null) {
                list.remove(data);
                changed = true;
            }
        }
        if (!changed) {
            return array;
        } else {
            T[] newArray = (T[]) Array.newInstance(array.getClass()
                    .getComponentType(), list.size());
            if (list.isEmpty()) {
                return newArray;
            }
            return list.toArray(newArray);
        }
    }
}
