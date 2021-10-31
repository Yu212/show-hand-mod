package com.yu212.showhandmod;

import net.minecraft.client.gui.screen.VideoSettingsScreen;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.client.settings.BooleanOption;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig;

@Mod("show-hand-mod")
public class ShowHandMod {
    public ShowHandMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onGuiOpen(GuiScreenEvent.InitGuiEvent.Post event) {
        if (event.getGui() instanceof VideoSettingsScreen) {
            VideoSettingsScreen gui = (VideoSettingsScreen)event.getGui();
            OptionsRowList optionsRowList = ObfuscationReflectionHelper.getPrivateValue(VideoSettingsScreen.class, gui, "field_146501_h");
            optionsRowList.addOption(new BooleanOption("showhandmod.options.show_hand", settings -> Config.SHOW_HAND.get(), (settings, value) -> Config.SHOW_HAND.set(value)), null);
        }
    }
}
