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
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.entity.player.Player;

public class ModKeyCallback {

    public static void onKeyPressed(ClientLevel clientWorld) {
        Minecraft mc = Minecraft.getInstance();
        ConfigOptions configs = QuickShulkerMod.getConfig();
        if (configs.openSettingGui.wasPressed()) {
            //#if MC >= 26.2
            //$$ mc.gui.setScreen(ModConfigMenu.getModConfigMenu(mc.gui.screen()));
            //#else
            mc.setScreen(ModConfigMenu.getModConfigMenu(mc.screen));
            //#endif
        }
        if (configs.keybinding.isKeybindPressed()) {
            Player player = mc.player;
            //#if MC >= 26.2
            //$$ if (mc.gui.screen() == null && QuickShulkerMod.getConfig().keybind && player != null && !player.isSpectator()) {
            //#else
            if (mc.screen == null && QuickShulkerMod.getConfig().keybind && player != null && !player.isSpectator()) {
            //#endif
                if (player.getMainHandItem().isEmpty() && !player.getOffhandItem().isEmpty())
                    ClientUtil.CheckAndSend(player.getOffhandItem(), 45);
                else
                    //#if MC >= 1.21.5
                    //$$ ClientUtil.CheckAndSend(player.getMainHandItem(), 36 + player.getInventory().getSelectedSlot());
                    //#else
                    ClientUtil.CheckAndSend(player.getMainHandItem(), 36 + player.getInventory().selected);
                    //#endif
            }
        }
    }
}
