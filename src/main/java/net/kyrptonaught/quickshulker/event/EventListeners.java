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

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.kyrptonaught.quickshulker.util.EnderChestSyncHandler;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;

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
    public static void containerOpenedListener(ServerPlayerEntity player, GenericContainerScreenHandler chestMenu) {
        EnderChestSyncHandler.syncOnContainerOpened(player, chestMenu);
    }

}
