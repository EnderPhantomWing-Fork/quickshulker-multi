/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.shulkerutils;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;

public class ShulkerUtils {
    public static boolean isShulkerItem(ItemStack item) {
        return Block.byItem(item.getItem()) instanceof ShulkerBoxBlock;
    }

    public static boolean shulkerContainsAny(Container shulkerInv, ItemStack stack) {
        for (int i = 0; i < shulkerInv.getContainerSize(); i++) {
            if (shulkerInv.getItem(i).getItem().equals(stack.getItem()))
                return true;
        }
        return false;
    }

    public static ItemStack insertIntoShulker(SimpleContainer shulkerInv, ItemStack stack, Player player) {
        if (isShulkerItem(stack) || !shulkerInv.canAddItem(stack))
            return stack;
        ItemStack output = shulkerInv.addItem(stack);
        shulkerInv.stopOpen(player);
        return output;
    }

    public static ItemStackInventory getInventoryFromShulker(ItemStack stack) {
        Block shulker = ((BlockItem) stack.getItem()).getBlock();
        if (shulker instanceof UpgradableShulker) {
            return new ItemStackInventory(stack, ((UpgradableShulker) shulker).QS$getInventorySize());
        }
        return new ItemStackInventory(stack, 27);
    }
}
