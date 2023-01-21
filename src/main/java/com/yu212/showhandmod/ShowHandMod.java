package com.yu212.showhandmod;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.VideoSettingsScreen;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.lwjgl.glfw.GLFW;

@Mod(ShowHandMod.MODID)
public class ShowHandMod {
    public static final String MODID = "showhandmod";
    public static final KeyMapping KEY_MAPPING = new KeyMapping("key.showhandmod.show_hand", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, "key.categories.misc");

    public ShowHandMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
    }

    @Mod.EventBusSubscriber(modid = MODID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KEY_MAPPING.consumeClick()) {
                Config.SHOW_HAND.set(!Config.SHOW_HAND.get());
            }
        }

        @SubscribeEvent
        public static void onGuiOpen(ScreenEvent.Init.Post event) {
            if (event.getScreen() instanceof VideoSettingsScreen) {
                OptionsList optionsRowList = (OptionsList)event.getScreen().children().get(0);
                optionsRowList.addSmall(OptionInstance.createBoolean("showhandmod.options.show_hand", Config.SHOW_HAND.get(), Config.SHOW_HAND::set), null);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {
        @SubscribeEvent
        public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(KEY_MAPPING);
        }
    }
}
