/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
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
                    //#if MC >= 1.21.5
                    //$$ ClientUtil.CheckAndSend(player.getMainHandStack(), 36 + player.getInventory().getSelectedSlot());
                    //#else
                    ClientUtil.CheckAndSend(player.getMainHandStack(), 36 + player.getInventory().selectedSlot);
                    //#endif
            }
        }
    }
}
