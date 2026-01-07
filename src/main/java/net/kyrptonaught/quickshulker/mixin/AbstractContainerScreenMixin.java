package net.kyrptonaught.quickshulker.mixin;

import net.kyrptonaught.quickshulker.util.MouseDraggedHandler;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerScreen.class)
public abstract class AbstractContainerScreenMixin {

    @Inject(
            method = "mouseClicked(Lnet/minecraft/client/input/MouseButtonEvent;Z)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void QS$mouseClicked(MouseButtonEvent click, boolean doubled, CallbackInfoReturnable<Boolean> cir){
        AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) (Object) this;
        boolean result = MouseDraggedHandler.beforeMouseClick(screen, click);
        if (result) {
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "mouseDragged(Lnet/minecraft/client/input/MouseButtonEvent;DD)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void QS$mouseDragged(MouseButtonEvent click, double offsetX, double offsetY, CallbackInfoReturnable<Boolean> cir){
        AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) (Object) this;
        boolean result = MouseDraggedHandler.beforeMouseDragged(screen, click);
        if(result){
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "mouseReleased(Lnet/minecraft/client/input/MouseButtonEvent;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void QS$mouseReleased(MouseButtonEvent click, CallbackInfoReturnable<Boolean> cir){
        AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) (Object) this;
        boolean result = MouseDraggedHandler.beforeMouseReleased(screen, click);
        if(result){
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "renderContents(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;renderLabels(Lnet/minecraft/client/gui/GuiGraphics;II)V",
                    shift = At.Shift.AFTER
            )
    )
    private void QS$drawForeground(GuiGraphics context, int mouseX, int mouseY, float deltaTicks, CallbackInfo ci){
        AbstractContainerScreen<?> screen  = (AbstractContainerScreen<?>) (Object) this;
        MouseDraggedHandler.beforeDrawForeground(screen, context, mouseX, mouseY);
    }
}
