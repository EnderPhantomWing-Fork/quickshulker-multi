/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.quickshulker.api;

import net.minecraft.world.item.ItemStack;

//Dummy to restore compat to mods already using this
public class ItemStackInventory extends net.kyrptonaught.shulkerutils.ItemStackInventory {
    public ItemStackInventory(ItemStack stack, int SIZE) {
        super(stack, SIZE);
    }
}
