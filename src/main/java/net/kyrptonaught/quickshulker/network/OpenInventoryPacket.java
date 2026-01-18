/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.quickshulker.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kyrptonaught.quickshulker.QuickShulkerMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
//#if MC >= 1.21.11
//$$ import net.minecraft.resources.Identifier;
//#endif

public class OpenInventoryPacket implements CustomPacketPayload {

    //#if MC <= 1.20.6
    //$$ public static final ResourceLocation OPEN_INV = ResourceLocation.tryBuild(QuickShulkerMod.MOD_ID, "open_inv");
    //#elseif MC >= 1.21.11
    //$$ public static final Identifier OPEN_INV = Identifier.fromNamespaceAndPath(QuickShulkerMod.MOD_ID, "open_inv");
    //#else
    public static final ResourceLocation OPEN_INV = ResourceLocation.fromNamespaceAndPath(QuickShulkerMod.MOD_ID, "open_inv");
    //#endif

    public static final Type<OpenInventoryPacket> OPEN_INV_ID = new CustomPacketPayload.Type<>(OPEN_INV);

    public static final StreamCodec<FriendlyByteBuf, OpenInventoryPacket> CODEC = StreamCodec.ofMember(OpenInventoryPacket::write, buf -> new OpenInventoryPacket());

    private void write(FriendlyByteBuf buf) {
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return OPEN_INV_ID;
    }

    public static void send(ServerPlayer player) {
        ServerPlayNetworking.send(player, new OpenInventoryPacket());
    }

}
