package net.kyrptonaught.quickshulker.mixin;

import net.kyrptonaught.quickshulker.util.MouseDraggedHandler;
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
            method = "mouseClicked(DDI)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private void QS$mouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir){
        AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) (Object) this;
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
        AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) (Object) this;
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
        AbstractContainerScreen<?> screen = (AbstractContainerScreen<?>) (Object) this;
        boolean result = MouseDraggedHandler.beforeMouseReleased(screen, mouseX, mouseY, button);
        if(result){
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "render(Lnet/minecraft/client/gui/GuiGraphics;IIF)V",
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
