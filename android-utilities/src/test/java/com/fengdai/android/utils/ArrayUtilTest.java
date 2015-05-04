package com.fengdai.android.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ArrayUtilTest {

    @Test
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