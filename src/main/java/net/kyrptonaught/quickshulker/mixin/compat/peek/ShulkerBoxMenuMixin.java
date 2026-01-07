package net.kyrptonaught.quickshulker.mixin.compat.peek;

import de.maxhenkel.peek.Peek;
import de.maxhenkel.peek.data.DataStore;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.kyrptonaught.quickshulker.compat.ModIds;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.ShulkerBoxMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Restriction(require = @Condition(ModIds.peek))
@Mixin(ShulkerBoxMenu.class)
public class ShulkerBoxMenuMixin {
    @Inject(
            method = "<init>(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/Container;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/inventory/AbstractContainerMenu;<init>(Lnet/minecraft/world/inventory/MenuType;I)V",
                    shift = At.Shift.AFTER
            )
    )
    private static void QS$setDataStoreNull(int syncId, Inventory playerInventory, Container inventory, CallbackInfo ci){
        if(Peek.CONFIG.showShulkerBoxBlockHint.get() && DataStore.lastOpenedShulkerBox != null){
            DataStore.lastOpenedShulkerBox = null;
        }
    }
}
