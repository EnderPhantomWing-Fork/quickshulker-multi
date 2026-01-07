package net.kyrptonaught.quickshulker.mixin;

import net.kyrptonaught.quickshulker.event.EventListeners;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// TODO(Ravel): can not resolve target class ServerPlayerEntity
@Mixin(ServerPlayer.class)
public class ContainerOpenMixin {
    // TODO(Ravel): no target class
    @Inject(method = "openMenu", at = @At("TAIL"))
    private void onOpenHandledScreen(MenuProvider factory, CallbackInfoReturnable<Boolean> cir){
        ServerPlayer player = (ServerPlayer) (Object) this;
        if(player.containerMenu instanceof ChestMenu chestMenu && chestMenu.getContainer() == player.getEnderChestInventory()){
            EventListeners.containerOpenedListener(player, chestMenu);
        }
    }
}
