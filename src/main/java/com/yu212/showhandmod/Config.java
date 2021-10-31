package com.yu212.showhandmod;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.BooleanValue SHOW_HAND = BUILDER.define("show_hand", true);
    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
