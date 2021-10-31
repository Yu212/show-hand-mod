package com.yu212.showhandmod.mixin;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.yu212.showhandmod.Config;
import net.minecraft.client.GameSettings;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GameRenderer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Redirect(method = "renderHand", at = @At(value = "FIELD", target = "Lnet/minecraft/client/GameSettings;hideGUI:Z", opcode = Opcodes.GETFIELD))
    public boolean hideHand(GameSettings instance, MatrixStack matrixStackIn, ActiveRenderInfo activeRenderInfoIn, float partialTicks) {
        return !Config.SHOW_HAND.get();
    }
}
