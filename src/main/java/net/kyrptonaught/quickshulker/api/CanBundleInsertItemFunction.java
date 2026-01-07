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

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;

@FunctionalInterface
public interface CanBundleInsertItemFunction {
    CanBundleInsertItemFunction ALWAYS = (player, inventory, hostStack, insertStack) -> true;

    boolean canBundleInsertItem(Player player, Container inventory, ItemStack hostStack, ItemStack insertStack);

}
