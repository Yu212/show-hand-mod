package com.yu212.showhandmod;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.EnumValue<HandStatus> HAND_VISIBILITY = BUILDER.defineEnum("hand_visibility", HandStatus.DEFAULT);
    public static final ForgeConfigSpec SPEC = BUILDER.build();
}
