package com.yu212.showhandmod;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.serialization.Codec;
import net.minecraft.client.*;
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

import java.util.Arrays;

@Mod(ShowHandMod.MODID)
public class ShowHandMod {
    public static final String MODID = "showhandmod";
    public static final KeyMapping KEY_MAPPING = new KeyMapping("key.showhandmod.switch_hand_visibility", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, "key.categories.misc");
    public static final OptionInstance<HandStatus> OPTION = new OptionInstance<>("options.showhandmod.hand_visibility", OptionInstance.noTooltip(), OptionInstance.forOptionEnum(),
            new OptionInstance.Enum<>(Arrays.asList(HandStatus.values()), Codec.INT.xmap(HandStatus::byId, HandStatus::getId)), Config.HAND_VISIBILITY.getDefault(), Config.HAND_VISIBILITY::set);

    public ShowHandMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
    }

    @Mod.EventBusSubscriber(modid = MODID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (KEY_MAPPING.consumeClick()) {
                Minecraft minecraft = Minecraft.getInstance();
                HandStatus currentStatus = Config.HAND_VISIBILITY.get();
                Config.HAND_VISIBILITY.set(currentStatus.getSwitchedStatus(minecraft.options.hideGui));
            }
        }

        @SubscribeEvent
        public static void onGuiOpen(ScreenEvent.Init.Post event) {
            if (event.getScreen() instanceof VideoSettingsScreen) {
                OptionsList optionsRowList = (OptionsList)event.getScreen().children().get(0);
                OPTION.set(Config.HAND_VISIBILITY.get());
                optionsRowList.addSmall(OPTION, null);
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
