package com.x29naybla.gardens_and_critters.common.entity;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;

public enum SnailVariant implements StringRepresentable {
    CREAM(0, "cream", true),
    GREEN(1, "green", true),
    BLACK(2, "black", true),
    LIME(3, "lime", true),
    LEMON(4, "lemon", true),
    MUSHROOM(5, "mushroom", false),
    PUMPKIN(6, "pumpkin", false),
    NAUTILUS(7, "nautilus", false);

    private static final SnailVariant[] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt(SnailVariant::getId)).toArray(SnailVariant[]::new);
    public static final Codec<SnailVariant> CODEC = StringRepresentable.fromEnum(SnailVariant::values);
    private final int id;
    private final String name;
    private final boolean common;

    SnailVariant(int id, String name, boolean common) {
        this.id = id;
        this.name = name;
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

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }
}
