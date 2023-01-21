package com.yu212.showhandmod.mixin;

import com.yu212.showhandmod.Config;
import net.minecraft.client.Options;
import net.minecraft.client.renderer.GameRenderer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Redirect(method = "renderItemInHand", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Options;hideGui:Z", opcode = Opcodes.GETFIELD))
    public boolean hideHand(Options instance) {
        return !Config.SHOW_HAND.get();
    }
}
