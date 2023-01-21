package com.yu212.showhandmod;

import net.minecraft.util.ByIdMap;
import net.minecraft.util.OptionEnum;

import java.util.function.IntFunction;

public enum HandStatus implements OptionEnum {
    DEFAULT(0, "options.showhandmod.default"),
    SHOW(1, "options.showhandmod.show"),
    HIDE(2, "options.showhandmod.hide");

    private static final IntFunction<HandStatus> BY_ID = ByIdMap.continuous(HandStatus::getId, values(), ByIdMap.OutOfBoundsStrategy.WRAP);
    private final int id;
    private final String key;

    HandStatus(int id, String key) {
        this.id = id;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public int getId() {
        return id;
    }

    public HandStatus getSwitchedStatus(boolean hideGui) {
        return switch (this) {
            case DEFAULT -> hideGui ? SHOW : HIDE;
            case HIDE -> SHOW;
            case SHOW -> HIDE;
        };
    }

    public boolean isHidingHand(boolean hideGui) {
        return switch (this) {
            case DEFAULT -> hideGui;
            case HIDE -> true;
            case SHOW -> false;
        };
    }

    public static HandStatus byId(int id) {
        return BY_ID.apply(id);
    }
}
