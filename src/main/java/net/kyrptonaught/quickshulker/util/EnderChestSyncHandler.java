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

package net.kyrptonaught.quickshulker.util;

import net.kyrptonaught.quickshulker.network.EnderChestS2CSyncPacket;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

public class EnderChestSyncHandler {

    public static void syncOnContainerOpened(ServerPlayerEntity player, GenericContainerScreenHandler chestMenu){
        syncEnderChestContent(player);
        chestMenu.addListener(new ScreenHandlerListener(){
            @Override
            public void onSlotUpdate(ScreenHandler handler, int slotId, ItemStack stack) {
                Slot slot = handler.getSlot(slotId);
                if(slot.inventory == player.getEnderChestInventory()){
                    EnderChestS2CSyncPacket.S2CEChestSlotPacket.send(player, slot.getIndex(), stack);
                }
            }
            @Override
            public void onPropertyUpdate(ScreenHandler handler, int property, int value) {

            }
        });
    }

    public static void syncEnderChestContent(ServerPlayerEntity player) {
        EnderChestS2CSyncPacket.S2CEChestContentPacket.send(player, player.getEnderChestInventory().getHeldStacks());
    }

    public static void setEnderChestContent(PlayerEntity player, List<ItemStack> itemStacks){
        SimpleInventory enderChestInventory = player.getEnderChestInventory();
        // safeguard against mods only changing ender chest size on one side
        int size = Math.min(itemStacks.size(), enderChestInventory.size());
        for(int i = 0; i < size; i++){
            enderChestInventory.setStack(i, itemStacks.get(i));
        }
    }

}
