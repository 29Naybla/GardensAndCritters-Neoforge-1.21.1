package com.x29naybla.gardens_and_critters.common.entity;

import java.util.Arrays;
import java.util.Comparator;

public enum SnailVariant {
    CREAM(0, true),
    GREEN(1, true),
    BLACK(2, true),
    LIME(3, true),
    LEMON(4, true),

    NAUTILUS(5, false);

    private static final SnailVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(SnailVariant::getId)).toArray(SnailVariant[]::new);
    private final int id;
    private final boolean common;

    SnailVariant(int id,  boolean common) {
        this.id = id;
        this.common = common;
    }

    public int getId() {
        return id;
    }

    public boolean isCommon() {
        return common;
    }

    public static SnailVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
