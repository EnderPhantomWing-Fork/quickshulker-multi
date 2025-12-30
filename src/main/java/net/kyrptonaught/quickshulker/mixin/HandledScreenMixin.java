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

import net.kyrptonaught.quickshulker.util.MouseDraggedHandler;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HandledScreen.class)
public abstract class HandledScreenMixin{

    @Inject(
            method = "mouseClicked(DDI)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void QS$mouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir){
        HandledScreen<?> screen = (HandledScreen<?>) (Object) this;
        boolean result = MouseDraggedHandler.beforeMouseClick(screen, mouseX, mouseY, button);
        if (result) {
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "mouseDragged(DDIDD)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void QS$mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY, CallbackInfoReturnable<Boolean> cir){
        HandledScreen<?> screen = (HandledScreen<?>) (Object) this;
        boolean result = MouseDraggedHandler.beforeMouseDragged(screen, mouseX, mouseY, button);
        if(result){
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "mouseReleased(DDI)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void QS$mouseReleased(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir){
        HandledScreen<?> screen = (HandledScreen<?>) (Object) this;
        boolean result = MouseDraggedHandler.beforeMouseReleased(screen, mouseX, mouseY, button);
        if(result){
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "render(Lnet/minecraft/client/gui/DrawContext;IIF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screen/ingame/HandledScreen;drawForeground(Lnet/minecraft/client/gui/DrawContext;II)V",
                    shift = At.Shift.AFTER
            )
    )
    private void QS$drawForeground(DrawContext context, int mouseX, int mouseY, float deltaTicks, CallbackInfo ci){
        HandledScreen<?> screen  = (HandledScreen<?>) (Object) this;
        MouseDraggedHandler.beforeDrawForeground(screen, context, mouseX, mouseY);
    }
}
