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

import net.kyrptonaught.quickshulker.util.MouseDraggedHandler;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
//#if MC >= 1.21.10
//$$ import net.minecraft.client.input.MouseButtonEvent;
//#endif

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {

    @Inject(
            //#if MC >= 1.21.10
            //$$ method = "mouseClicked(Lnet/minecraft/client/input/MouseButtonEvent;Z)Z",
            //#else
            method = "mouseClicked(DDI)Z",
            //#endif
            at = @At("HEAD"),
            cancellable = true
    )
    //#if MC >= 1.21.10
    //$$ private void QS$mouseClicked(MouseButtonEvent click, boolean doubled, CallbackInfoReturnable<Boolean> cir){
    //$$     AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) (Object) this;
    //$$     boolean result = MouseDraggedHandler.beforeMouseClick(screen, click);
    //#else
    private void QS$mouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir){
        AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) (Object) this;
        boolean result = MouseDraggedHandler.beforeMouseClick(screen, mouseX, mouseY, button);
    //#endif
        if (result) {
            cir.setReturnValue(true);
        }
    }

    @Inject(
            //#if MC >= 1.21.10
            //$$ method = "mouseDragged(Lnet/minecraft/client/input/MouseButtonEvent;DD)Z",
            //#else
            method = "mouseDragged(DDIDD)Z",
            //#endif
            at = @At("HEAD"),
            cancellable = true
    )
    //#if MC >= 1.21.10
    //$$ private void QS$mouseDragged(MouseButtonEvent click, double offsetX, double offsetY, CallbackInfoReturnable<Boolean> cir){
    //$$     AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) (Object) this;
    //$$     boolean result = MouseDraggedHandler.beforeMouseDragged(screen, click);
    //#else
    private void QS$mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY, CallbackInfoReturnable<Boolean> cir){
        AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) (Object) this;
        boolean result = MouseDraggedHandler.beforeMouseDragged(screen, mouseX, mouseY, button);
    //#endif
        if(result){
            cir.setReturnValue(true);
        }
    }

    @Inject(
            //#if MC >= 1.21.10
            //$$ method = "mouseReleased(Lnet/minecraft/client/input/MouseButtonEvent;)Z",
            //#else
            method = "mouseReleased(DDI)Z",
            //#endif
            at = @At("HEAD"),
            cancellable = true
    )
    //#if MC >= 1.21.10
    //$$ private void QS$mouseReleased(MouseButtonEvent click, CallbackInfoReturnable<Boolean> cir){
    //$$     AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) (Object) this;
    //$$     boolean result = MouseDraggedHandler.beforeMouseReleased(screen, click);
    //#else
    private void QS$mouseReleased(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir){
        AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) (Object) this;
        boolean result = MouseDraggedHandler.beforeMouseReleased(screen, mouseX, mouseY, button);
    //#endif
        if(result){
            cir.setReturnValue(true);
        }
    }

    @Inject(
            //#if MC >= 26.1
            //$$ method = "extractContents(Lnet/minecraft/client/gui/GuiGraphicsExtractor;IIF)V",
            //#elseif MC >= 1.21.6
            //$$ method = "renderContents(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
            //#else
            method = "render(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
            //#endif
            at = @At(
                    value = "INVOKE",
                    //#if MC >= 26.1
                    //$$ target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;extractLabels(Lnet/minecraft/client/gui/GuiGraphicsExtractor;II)V",
                    //#else
                    target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;renderLabels(Lnet/minecraft/client/gui/GuiGraphics;II)V",
                    //#endif
                    shift = At.Shift.AFTER
            )
    )
    private void QS$drawForeground(GuiGraphics context, int mouseX, int mouseY, float deltaTicks, CallbackInfo ci){
        AbstractContainerScreen<?> screen  = (AbstractContainerScreen<?>) (Object) this;
        MouseDraggedHandler.beforeDrawForeground(screen, context, mouseX, mouseY);
    }
}
