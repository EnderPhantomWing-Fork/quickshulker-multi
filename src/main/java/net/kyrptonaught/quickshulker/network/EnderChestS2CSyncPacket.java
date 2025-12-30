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
import net.kyrptonaught.quickshulker.util.PacketUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.List;

public class EnderChestS2CSyncPacket {

    public record S2CEChestContentPacket(List<ItemStack> itemStacks) implements CustomPayload {

        public static final Id<S2CEChestContentPacket> S2C_ECHEST_CONTENT_PACKET_ID = new Id<>(Identifier.of(QuickShulkerMod.MOD_ID, "s2c_echest_content_packet"));
        public static final PacketCodec<RegistryByteBuf, S2CEChestContentPacket> CODEC = PacketCodec.tuple(ItemStack.OPTIONAL_LIST_PACKET_CODEC, S2CEChestContentPacket::itemStacks, S2CEChestContentPacket::new);

        public static void send(ServerPlayerEntity player, List<ItemStack> itemStacks) {
            ServerPlayNetworking.send(player, new S2CEChestContentPacket(itemStacks));
        }

        @Override
        public Id<? extends CustomPayload> getId() {
            return S2C_ECHEST_CONTENT_PACKET_ID;
        }
    }

    public record S2CEChestSlotPacket(int slotId, ItemStack itemStack) implements CustomPayload{

        public static final Id<S2CEChestSlotPacket> S2C_ECHEST_SLOT_PACKET_ID = new Id<>(Identifier.of(QuickShulkerMod.MOD_ID, "s2c_echest_slot_packet"));
        public static final PacketCodec<RegistryByteBuf, S2CEChestSlotPacket> CODEC = PacketCodec.of(
                (value, buf) -> {
                    buf.writeInt(value.slotId);
                    PacketUtils.writeItemStack(buf, value.itemStack);},
                buf -> new S2CEChestSlotPacket(buf.readInt(), PacketUtils.readItemStack(buf)));

        public static void send(ServerPlayerEntity player, int slotId, ItemStack itemStack) {
            ServerPlayNetworking.send(player, new S2CEChestSlotPacket(slotId, itemStack));
        }

        @Override
        public Id<? extends CustomPayload> getId() {
            return S2C_ECHEST_SLOT_PACKET_ID;
        }
    }
}
