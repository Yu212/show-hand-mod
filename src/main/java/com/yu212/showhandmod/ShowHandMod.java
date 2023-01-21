package com.yu212.showhandmod;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.VideoSettingsScreen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(ShowHandMod.MODID)
public class ShowHandMod {
    public static final String MODID = "showhandmod";

    public ShowHandMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
    }

    @Mod.EventBusSubscriber(modid = MODID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onGuiOpen(ScreenEvent.Init.Post event) {
            if (event.getScreen() instanceof VideoSettingsScreen) {
                OptionsList optionsRowList = (OptionsList)event.getScreen().children().get(0);
                optionsRowList.addSmall(OptionInstance.createBoolean("showhandmod.options.show_hand", Config.SHOW_HAND.get(), Config.SHOW_HAND::set), null);
            }
        }
    }
}
