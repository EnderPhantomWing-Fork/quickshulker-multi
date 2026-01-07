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

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.kyrptonaught.quickshulker.util.EnderChestSyncHandler;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.server.level.ServerPlayer;

public class EventListeners {

    public static void registerEventListeners() {

        // Log in
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            EnderChestSyncHandler.syncEnderChestContent(handler.player);
        });

        // Respawn
        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
            EnderChestSyncHandler.syncEnderChestContent(newPlayer);
        });

        // Change dimension
        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
            EnderChestSyncHandler.syncEnderChestContent(player);
        });

    }

    // Open inventory
    public static void containerOpenedListener(ServerPlayer player, ChestMenu chestMenu) {
        EnderChestSyncHandler.syncOnContainerOpened(player, chestMenu);
    }

}
