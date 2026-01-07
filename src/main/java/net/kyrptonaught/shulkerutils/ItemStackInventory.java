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

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;

import java.util.Objects;


public class ItemStackInventory extends SimpleContainer {
    protected final ItemStack itemStack;
    protected final int SIZE;

    public ItemStackInventory(ItemStack stack, int SIZE) {
        super(getStacks(stack, SIZE).toArray(new ItemStack[SIZE]));
        itemStack = stack;
        this.SIZE = SIZE;
    }

    public static NonNullList<ItemStack> getStacks(ItemStack usedStack, int SIZE) {
        NonNullList<ItemStack> itemStacks = NonNullList.withSize(SIZE, ItemStack.EMPTY);
        Objects.requireNonNull(usedStack.getComponents().get(DataComponents.CONTAINER)).copyInto(itemStacks);
        return itemStacks;
    }

    @Override
    public void setChanged() {
        super.setChanged();
        NonNullList<ItemStack> itemStacks = NonNullList.withSize(SIZE, ItemStack.EMPTY);
        for (int i = 0; i < getContainerSize(); i++) {
            itemStacks.set(i, getItem(i));
        }
        itemStack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(itemStacks));
    }

    @Override
    public void stopOpen(Player playerEntity) {
        if (itemStack.getCount() > 1) {
            int count = itemStack.getCount();
            itemStack.setCount(1);
            playerEntity.addItem(new ItemStack(itemStack.getItem(), count - 1));
        }
        setChanged();
    }
}