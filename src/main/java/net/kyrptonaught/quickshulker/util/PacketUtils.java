/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.quickshulker.util;

import net.minecraft.world.item.ItemStack;
import net.minecraft.network.RegistryFriendlyByteBuf;

public class PacketUtils{

    public static void writeItemStack(RegistryFriendlyByteBuf buf, ItemStack itemStack) {
        ItemStack.OPTIONAL_STREAM_CODEC.encode(buf, itemStack);
    }

    public static ItemStack readItemStack(RegistryFriendlyByteBuf buf) {
        return ItemStack.OPTIONAL_STREAM_CODEC.decode(buf);
    }

}
