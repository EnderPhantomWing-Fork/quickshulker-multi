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

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kyrptonaught.quickshulker.QuickShulkerMod;
import net.kyrptonaught.quickshulker.api.Util;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record OpenShulkerPacket(int invSlot) implements CustomPayload {

    public static final Identifier OPEN_SHULKER_PACKET = Identifier.of(QuickShulkerMod.MOD_ID, "open_shulker_packet");

    public static final Id<OpenShulkerPacket> OPEN_SHULKER_PACKET_ID = new Id<>(OPEN_SHULKER_PACKET);

    public static final PacketCodec<PacketByteBuf, OpenShulkerPacket> CODEC = PacketCodec.of((value, buf) -> buf.writeInt(value.invSlot), buf -> new OpenShulkerPacket(buf.readInt()));

    public static void registerReceivePacket() {
        PayloadTypeRegistry.playC2S().register(OpenShulkerPacket.OPEN_SHULKER_PACKET_ID, OpenShulkerPacket.CODEC);
        PayloadTypeRegistry.playS2C().register(OpenShulkerPacket.OPEN_SHULKER_PACKET_ID, OpenShulkerPacket.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(OpenShulkerPacket.OPEN_SHULKER_PACKET_ID, (payload, context) -> context.server().execute(() -> Util.openItem(context.player(), payload.invSlot)));
    }

    @Environment(EnvType.CLIENT)
    public static void sendOpenPacket(int invSlot) {
        ClientPlayNetworking.send(new OpenShulkerPacket(invSlot));
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return OPEN_SHULKER_PACKET_ID;
    }
}