package com.x29naybla.gardens_and_critters.common.entity;

import java.util.Arrays;
import java.util.Comparator;

public enum SnailVariant {
    CREAM(0),
    GREEN(1),
    NAUTILUS(2);

    private static final SnailVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(SnailVariant::getId)).toArray(SnailVariant[]::new);
    private final int id;

    SnailVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static SnailVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}
