package com.fengdai.android.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {

    /**
     * Remove all null element in the given array. Will return null if the array is null.
     *
     * @return A new array if any element is removed, or the origin array if nothing removed.
     */
    public static <T> T[] removeNull(T[] array) {
        if (array == null) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        boolean changed = false;
        for (T element : array) {
            if (element != null) {
                list.add(element);
            } else {
                changed = true;
            }
        }
        if (!changed) {
            return array;
        } else {
            @SuppressWarnings({"unchecked"})
            T[] newArray = (T[]) Array.newInstance(array.getClass().getComponentType(), list.size());
            return list.isEmpty() ? newArray : list.toArray(newArray);
        }
    }
}
