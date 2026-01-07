package net.kyrptonaught.quickshulker.mixin;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.ClickType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

// TODO(Ravel): can not resolve target class HandledScreen
@Mixin(AbstractContainerScreen.class)
public interface AbstractContainerScreenInvoker {
    // TODO(Ravel): Could not determine a single target
    @Invoker("getHoveredSlot")
    Slot QS$getSlotAt(double mouseX, double mouseY);

    // TODO(Ravel): Could not determine a single target
    @Invoker("slotClicked")
    void QS$onMouseClick(Slot slot, int slotId, int button, ClickType actionType);
}
