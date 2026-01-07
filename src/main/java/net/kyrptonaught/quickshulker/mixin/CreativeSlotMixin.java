package net.kyrptonaught.quickshulker.mixin;

import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

// TODO(Ravel): can not resolve target class CreativeInventoryScreen.CreativeSlot
@Mixin(CreativeModeInventoryScreen.SlotWrapper.class)
public interface CreativeSlotMixin {

    // TODO(Ravel): Could not determine a single target
    @Accessor(value = "target")
    Slot getTarget();
}
