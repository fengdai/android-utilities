package com.fengdai.android.tests;

import com.fengdai.android.utils.ArrayUtil;

import junit.framework.TestCase;

import java.util.Arrays;

public class ArrayUtilTest extends TestCase {

    public void testRemoveNull() {
        assertArray(null, null);
        assertArray(new String[]{"A", null, null, "D"}, new String[]{"A", "D"});
        assertArray(new String[]{null, null, null, null}, new String[0]);
        assertArray(new String[]{}, new String[0]);
    }

    private static <T> void assertArray(T[] original, T[] expected) {
        T[] actual = ArrayUtil.removeNull(original);
        assertNoNullElement(actual);
        assertTrue(Arrays.equals(expected, actual));
    }

    private static <T> void assertNoNullElement(T[] array) {
        if (array != null) {
            for (T element : array) {
                assertNotNull(element);
            }
        }
    }
}