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

package net.kyrptonaught.shulkerutils;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
//#if MC >= 1.21.10
//$$ import net.minecraft.entity.ContainerUser;
//#else
//#endif

import java.util.Objects;


public class ItemStackInventory extends SimpleInventory {
    protected final ItemStack itemStack;
    protected final int SIZE;

    public ItemStackInventory(ItemStack stack, int SIZE) {
        super(getStacks(stack, SIZE).toArray(new ItemStack[SIZE]));
        itemStack = stack;
        this.SIZE = SIZE;
    }

    public static DefaultedList<ItemStack> getStacks(ItemStack usedStack, int SIZE) {
        DefaultedList<ItemStack> itemStacks = DefaultedList.ofSize(SIZE, ItemStack.EMPTY);
        Objects.requireNonNull(usedStack.getComponents().get(DataComponentTypes.CONTAINER)).copyTo(itemStacks);
        return itemStacks;
    }

    @Override
    public void markDirty() {
        super.markDirty();
        DefaultedList<ItemStack> itemStacks = DefaultedList.ofSize(SIZE, ItemStack.EMPTY);
        for (int i = 0; i < size(); i++) {
            itemStacks.set(i, getStack(i));
        }
        itemStack.set(DataComponentTypes.CONTAINER, ContainerComponent.fromStacks(itemStacks));
    }

    //#if MC >= 1.21.10
    //@Override
    //$$ public void onClose(ContainerUser user) {
    //$$     if (itemStack.getCount() > 1) {
    //$$         int count = itemStack.getCount();
    //$$         itemStack.setCount(1);
    //$$         ((PlayerEntity) user).giveItemStack(new ItemStack(itemStack.getItem(), count - 1));
    //$$     }
    //$$     markDirty();
    //$$ }
    //#else
    @Override
    public void onClose(PlayerEntity playerEntity) {
        if (itemStack.getCount() > 1) {
            int count = itemStack.getCount();
            itemStack.setCount(1);
            playerEntity.giveItemStack(new ItemStack(itemStack.getItem(), count - 1));
        }
        markDirty();
    }
    //#endif
}