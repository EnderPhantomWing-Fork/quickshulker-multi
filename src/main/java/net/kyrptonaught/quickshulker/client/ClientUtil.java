/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.quickshulker.client;

import net.kyrptonaught.quickshulker.api.Util;
import net.kyrptonaught.quickshulker.mixin.CreativeSlotMixin;
import net.kyrptonaught.quickshulker.network.OpenShulkerPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;

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

    public static boolean isCreativeScreen(Player player) {
        return player.containerMenu instanceof CreativeModeInventoryScreen.ItemPickerMenu;

    }

    public static int getSlotId(AbstractContainerMenu handler, Slot slot) {
        if (handler instanceof CreativeModeInventoryScreen.ItemPickerMenu) {
            //#if MC >= 26.2
            //$$ if (((CreativeModeInventoryScreen) Minecraft.getInstance().gui.screen()).isInventoryOpen() && slot instanceof CreativeModeInventoryScreen.SlotWrapper) {
            //#else
            if (Minecraft.getInstance().screen != null && ((CreativeModeInventoryScreen) Minecraft.getInstance().screen).isInventoryOpen() && slot instanceof CreativeModeInventoryScreen.SlotWrapper) {
                //#endif
                return ((CreativeSlotMixin) slot).getTarget().index;
            }
        }
        return slot.index;
    }

    public static int getPlayerInvSlot(AbstractContainerMenu handler, Slot slot) {
        if (handler instanceof CreativeModeInventoryScreen.ItemPickerMenu) {
            //#if MC >= 26.2
            //$$ if (((CreativeModeInventoryScreen) Minecraft.getInstance().gui.screen()).isInventoryOpen() && slot instanceof CreativeModeInventoryScreen.SlotWrapper) {
            //#else
            if (Minecraft.getInstance().screen != null && ((CreativeModeInventoryScreen) Minecraft.getInstance().screen).isInventoryOpen() && slot instanceof CreativeModeInventoryScreen.SlotWrapper) {
                //#endif
                return ((CreativeSlotMixin) slot).getTarget().getContainerSlot();
            }
        }
        return slot.getContainerSlot();
    }
}
