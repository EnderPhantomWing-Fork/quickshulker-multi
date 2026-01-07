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

import net.kyrptonaught.quickshulker.event.EventListeners;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public class ContainerOpenMixin {
    @Inject(method = "openMenu", at = @At("TAIL"))
    private void onOpenHandledScreen(MenuProvider factory, CallbackInfoReturnable<Boolean> cir){
        ServerPlayer player = (ServerPlayer) (Object) this;
        if(player.containerMenu instanceof ChestMenu chestMenu && chestMenu.getContainer() == player.getEnderChestInventory()){
            EventListeners.containerOpenedListener(player, chestMenu);
        }
    }
}
