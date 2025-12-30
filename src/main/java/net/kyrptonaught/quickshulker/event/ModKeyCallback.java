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

package net.kyrptonaught.quickshulker.event;

import net.kyrptonaught.quickshulker.QuickShulkerMod;
import net.kyrptonaught.quickshulker.client.ClientUtil;
import net.kyrptonaught.quickshulker.config.ConfigOptions;
import net.kyrptonaught.quickshulker.config.ModConfigMenu;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;

public class ModKeyCallback {

    public static void onKeyPressed(ClientWorld clientWorld){
        MinecraftClient mc = MinecraftClient.getInstance();
        ConfigOptions configs = QuickShulkerMod.getConfig();
        if(configs.openSettingGui.wasPressed()){
            mc.setScreen(ModConfigMenu.getModConfigMenu(mc.currentScreen));
        }
        if (configs.keybinding.isKeybindPressed()) {
            PlayerEntity player = mc.player;
            if (mc.currentScreen == null && QuickShulkerMod.getConfig().keybind && player != null && !player.isSpectator()) {
                if (player.getMainHandStack().isEmpty() && !player.getOffHandStack().isEmpty())
                    ClientUtil.CheckAndSend(player.getOffHandStack(), 45);
                else
                    ClientUtil.CheckAndSend(player.getMainHandStack(), 36 + player.getInventory().selectedSlot);
            }
        }
    }
}
