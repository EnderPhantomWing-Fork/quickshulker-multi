/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.quickshulker.mixin;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.kyrptonaught.quickshulker.QuickShulkerMod;
import net.kyrptonaught.quickshulker.client.ClientUtil;
import net.kyrptonaught.quickshulker.client.QuickShulkerModClient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
//#if MC >= 1.21.10
//$$ import net.minecraft.client.input.MouseButtonEvent;
//$$ import net.minecraft.client.input.KeyEvent;
//#endif
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerScreen.class)
@Environment(EnvType.CLIENT)
public abstract class ScreenMixin {
    @Shadow
    protected Slot hoveredSlot;

    @Shadow
    @Final
    protected AbstractContainerMenu menu;

    @Shadow
    private boolean skipNextRelease;

    @Inject(method = "init", at = @At("TAIL"))
    private void fixMouse(CallbackInfo ci) {
        if (QuickShulkerMod.lastMouseX != 0 && QuickShulkerMod.lastMouseY != 0) {
            //#if MC >= 1.21.10
            //$$ GLFW.glfwSetCursorPos(Minecraft.getInstance().getWindow().handle(), QuickShulkerMod.lastMouseX, QuickShulkerMod.lastMouseY);
            //#else
            GLFW.glfwSetCursorPos(Minecraft.getInstance().getWindow().getWindow(), QuickShulkerMod.lastMouseX, QuickShulkerMod.lastMouseY);
            //#endif
            QuickShulkerMod.lastMouseY = 0;
            QuickShulkerMod.lastMouseX = 0;
        }
    }

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    //#if MC >= 1.21.10
    //$$ private void QS$keyPressed(KeyEvent input, CallbackInfoReturnable<Boolean> cir) {
    //$$     if (QuickShulkerMod.getConfig().keybingInInv) {
    //$$         if (QuickShulkerModClient.getKeybinding().matches(input.input(), InputConstants.Type.KEYSYM)) {
    //#else
    private void QS$keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        if (QuickShulkerMod.getConfig().keybingInInv) {
            if (QuickShulkerModClient.getKeybinding().matches(keyCode, InputConstants.Type.KEYSYM)) {
                //#endif
                if (handleTrigger())
                    cir.setReturnValue(true);
            }
        }
    }

    @Inject(method = "mouseClicked", at = @At("HEAD"), cancellable = true)
    //#if MC >= 1.21.10
    //$$ private void QS$mousePressed(MouseButtonEvent click, boolean doubled, CallbackInfoReturnable<Boolean> cir) {
    //$$     if (QuickShulkerMod.getConfig().rightClickInv) {
    //$$         if (this.menu.getCarried().isEmpty() && click.button() == 1 && this.hoveredSlot != null && this.hoveredSlot.getItem().getCount() == 1) {
    //#else
    private void QS$mousePressed(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        if (QuickShulkerMod.getConfig().rightClickInv) {
            if (this.menu.getCarried().isEmpty() && button == 1 && this.hoveredSlot != null && this.hoveredSlot.getItem().getCount() == 1) {
    //#endif
                if (handleTrigger()) {
                    this.skipNextRelease = true;
                    cir.setReturnValue(true);
                    return;
                }
            }
        }
        if (QuickShulkerMod.getConfig().keybingInInv) {
            //#if MC >= 1.21.10
            //$$ if (QuickShulkerModClient.getKeybinding().matches(click.button(), InputConstants.Type.MOUSE)) {
            //#else
            if (QuickShulkerModClient.getKeybinding().matches(button, InputConstants.Type.MOUSE)) {
            //#endif
                if (handleTrigger()) {
                    this.skipNextRelease = true;
                    cir.setReturnValue(true);
                }
            }
        }
    }

    @Unique
    private boolean handleTrigger() {
        if (this.hoveredSlot != null) {
            return isValid(this.hoveredSlot.getItem(), ClientUtil.getSlotId(menu, this.hoveredSlot));
        }
        return false;
    }

    @Unique
    private boolean isValid(ItemStack stack, int id) {
        if (this.hoveredSlot.container instanceof Inventory)
            if (ClientUtil.CheckAndSend(stack, id)) {
                QuickShulkerMod.lastMouseX = Minecraft.getInstance().mouseHandler.xpos();
                QuickShulkerMod.lastMouseY = Minecraft.getInstance().mouseHandler.ypos();
                return true;
            }
        return false;
    }
}
