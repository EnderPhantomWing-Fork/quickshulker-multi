package net.kyrptonaught.quickshulker.mixin;

import net.kyrptonaught.quickshulker.api.ItemInventoryContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.core.NonNullList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


// TODO(Ravel): can not resolve target class ScreenHandler
@Mixin(AbstractContainerMenu.class)
public abstract class ContainerMixin implements ItemInventoryContainer {

    int playerInvSlot = -1;

    public int getUsedSlotInPlayerInv() {
        return playerInvSlot;
    }

    public void setUsedSlot(int playerInvSlotID) {
        this.playerInvSlot = playerInvSlotID;
    }

    // TODO(Ravel): Could not determine a single target
    @Shadow
    @Final
    public NonNullList<Slot> slots;

    // TODO(Ravel): no target class
    @Inject(method = "clicked", at = @At("HEAD"), cancellable = true)
    public void QS$onClick(int slotId, int button, ClickType actionType, Player player, CallbackInfo ci) {
        if (slotId > 0 && slotId < slots.size()) {
            if (hasItem())
                if (slots.get(slotId).container instanceof Inventory && slots.get(slotId).getContainerSlot() == playerInvSlot)
                    ci.cancel();
        }
    }
}