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

package net.kyrptonaught.quickshulker.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kyrptonaught.quickshulker.QuickShulkerMod;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class OpenInventoryPacket implements CustomPayload {

    public static final Identifier OPEN_INV = Identifier.of(QuickShulkerMod.MOD_ID, "open_inv");

    public static final Id<OpenInventoryPacket> OPEN_INV_ID = new CustomPayload.Id<>(OPEN_INV);

    public static final PacketCodec<PacketByteBuf, OpenInventoryPacket> CODEC = PacketCodec.of(OpenInventoryPacket::write, buf -> new OpenInventoryPacket());

    private void write(PacketByteBuf buf) {
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return OPEN_INV_ID;
    }

    public static void send(ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new OpenInventoryPacket());
    }

}
