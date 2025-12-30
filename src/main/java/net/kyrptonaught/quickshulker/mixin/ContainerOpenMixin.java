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

import net.kyrptonaught.quickshulker.event.EventListeners;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public class ContainerOpenMixin {
    @Inject(method = "openHandledScreen", at = @At("TAIL"))
    private void onOpenHandledScreen(NamedScreenHandlerFactory factory, CallbackInfoReturnable<Boolean> cir){
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        if(player.currentScreenHandler instanceof GenericContainerScreenHandler chestMenu && chestMenu.getInventory() == player.getEnderChestInventory()){
            EventListeners.containerOpenedListener(player, chestMenu);
        }
    }
}
