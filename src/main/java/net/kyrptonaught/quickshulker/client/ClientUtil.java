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

package net.kyrptonaught.quickshulker.client;

import net.kyrptonaught.quickshulker.api.Util;
import net.kyrptonaught.quickshulker.mixin.CreativeSlotMixin;
import net.kyrptonaught.quickshulker.network.OpenShulkerPacket;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.CreativeInventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;

public class ClientUtil {

    public static boolean CheckAndSend(ItemStack stack, int slot) {
        if (Util.isOpenableItem(stack)) {
            SendOpenPacket(slot);
            return true;
        }
        return false;
    }

    private static void SendOpenPacket(int slot) {
        OpenShulkerPacket.sendOpenPacket(slot);
    }

    public static boolean isCreativeScreen(PlayerEntity player) {
        return player.currentScreenHandler instanceof CreativeInventoryScreen.CreativeScreenHandler;

    }

    public static int getSlotId(ScreenHandler handler, Slot slot) {
        if (handler instanceof CreativeInventoryScreen.CreativeScreenHandler) {
            if (((CreativeInventoryScreen) MinecraftClient.getInstance().currentScreen).isInventoryTabSelected() && slot instanceof CreativeInventoryScreen.CreativeSlot) {
                return ((CreativeSlotMixin) slot).getSlot().id;
            } else {
                return slot.id - 9;
            }
        }
        return slot.id;
    }

    public static int getPlayerInvSlot(ScreenHandler handler, Slot slot) {
        if (handler instanceof CreativeInventoryScreen.CreativeScreenHandler) {
            if (((CreativeInventoryScreen) MinecraftClient.getInstance().currentScreen).isInventoryTabSelected() && slot instanceof CreativeInventoryScreen.CreativeSlot) {
                return ((CreativeSlotMixin) slot).getSlot().getIndex();
            }
        }
        return slot.getIndex();
    }
}
