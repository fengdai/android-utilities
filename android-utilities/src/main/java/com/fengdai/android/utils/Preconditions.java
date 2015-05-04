package com.fengdai.android.utils;

import android.text.TextUtils;

import java.util.Collection;

public class Preconditions {
    public static <T extends CharSequence> T nonBlank(T text, String name) {
        if (TextUtils.isEmpty(text)) {
            throw new IllegalArgumentException(name + " must not be blank");
        } else {
            return text;
        }
    }

    public static <T extends Collection> T nonEmpty(T collection, String name) {
        if (nonNull(collection, name).isEmpty()) {
            throw new IllegalArgumentException(name + " must not be empty");
        } else {
            return collection;
        }
    }

    public static <T> T nonNull(T reference, String name) {
        if (reference == null) {
            throw new NullPointerException(name + " must not be null");
        } else {
            return reference;
        }
    }

    private Preconditions() {
        throw new AssertionError();
    }
}
