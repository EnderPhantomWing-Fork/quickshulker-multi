/*
 * This file is part of the Quick Shulker Multi project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2025  Fallen_Breath and contributors
 *
 * Quick Shulker Multi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Quick Shulker Multi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Quick Shulker Multi.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.kyrptonaught.quickshulker.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyrptonaught.quickshulker.QuickShulkerMod;
import net.kyrptonaught.quickshulker.client.ClientUtil;
import net.kyrptonaught.quickshulker.client.QuickShulkerModClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//#if MC >= 1.21.10
//$$ import net.minecraft.client.gui.Click;
//$$ import net.minecraft.client.input.KeyInput;
//#else
//#endif

@Mixin(HandledScreen.class)
@Environment(EnvType.CLIENT)
public abstract class ScreenMixin {
    @Shadow
    protected Slot focusedSlot;

    @Shadow
    @Final
    protected ScreenHandler handler;

    @Shadow private boolean cancelNextRelease;

    @Inject(method = "init", at = @At("TAIL"))
    private void fixMouse(CallbackInfo ci) {
        if (QuickShulkerMod.lastMouseX != 0 && QuickShulkerMod.lastMouseY != 0) {
            GLFW.glfwSetCursorPos(MinecraftClient.getInstance().getWindow().getHandle(), QuickShulkerMod.lastMouseX, QuickShulkerMod.lastMouseY);
            QuickShulkerMod.lastMouseY = 0;
            QuickShulkerMod.lastMouseX = 0;
        }
    }

    //#if MC >= 1.21.10
    //$$ @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    //$$ private void QS$keyPressed(KeyInput input, CallbackInfoReturnable<Boolean> cir) {
    //$$     if (QuickShulkerMod.getConfig().keybingInInv) {
    //$$         if (QuickShulkerModClient.getKeybinding().matches(input.getKeycode(), InputUtil.Type.KEYSYM)) {
    //$$             if (handleTrigger())
    //$$                 cir.setReturnValue(true);
    //$$         }
    //$$     }
    //$$ }
    //
    //$$ @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    //$$ private void QS$mousePressed(Click click, boolean doubled, CallbackInfoReturnable<Boolean> cir) {
    //$$     if (QuickShulkerMod.getConfig().rightClickInv) {
    //$$         if (this.handler.getCursorStack().isEmpty() && click.button() == 1 && this.focusedSlot != null && this.focusedSlot.getStack().getCount() == 1) {
    //$$             if (handleTrigger()) {
    //$$                 this.cancelNextRelease = true;
    //$$                 cir.setReturnValue(true);
    //$$                 return;
    //$$             }
    //$$         }
    //$$     }
    //$$     if (QuickShulkerMod.getConfig().keybingInInv) {
    //$$         if (QuickShulkerModClient.getKeybinding().matches(click.button(), InputUtil.Type.MOUSE)) {
    //$$             if (handleTrigger()) {
    //$$                 this.cancelNextRelease = true;
    //$$                 cir.setReturnValue(true);
    //$$                 return;
    //$$             }
    //$$         }
    //$$     }
    //$$ }
    //#else
    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void QS$keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (QuickShulkerMod.getConfig().keybingInInv) {
            if (QuickShulkerModClient.getKeybinding().matches(keyCode, InputUtil.Type.KEYSYM)) {
                if (handleTrigger())
                    cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    private void QS$mousePressed(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (QuickShulkerMod.getConfig().rightClickInv) {
            if (this.handler.getCursorStack().isEmpty() && button == 1 && this.focusedSlot != null && this.focusedSlot.getStack().getCount() == 1) {
                if (handleTrigger()) {
                    this.cancelNextRelease = true;
                    cir.setReturnValue(true);
                    return;
                }
            }
        }
        if (QuickShulkerMod.getConfig().keybingInInv) {
            if (QuickShulkerModClient.getKeybinding().matches(button, InputUtil.Type.MOUSE)) {
                if (handleTrigger()) {
                    this.cancelNextRelease = true;
                    cir.setReturnValue(true);
                    return;
                }
            }
        }
    }
    //#endif

    @Unique
    private boolean handleTrigger() {
        if (this.focusedSlot != null) {
            return isValid(this.focusedSlot.getStack(), ClientUtil.getSlotId(handler, this.focusedSlot));
        }
        return false;
    }

    @Unique
    private boolean isValid(ItemStack stack, int id) {
        if (this.focusedSlot.inventory instanceof PlayerInventory)
            if (ClientUtil.CheckAndSend(stack, id)) {
                QuickShulkerMod.lastMouseX = MinecraftClient.getInstance().mouse.getX();
                QuickShulkerMod.lastMouseY = MinecraftClient.getInstance().mouse.getY();
                return true;
            }
        return false;
    }
}
