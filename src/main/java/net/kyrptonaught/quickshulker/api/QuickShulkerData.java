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

package net.kyrptonaught.quickshulker.api;

import net.kyrptonaught.quickshulker.QuickShulkerMod;
import net.kyrptonaught.shulkerutils.ShulkerUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class QuickShulkerData {
    public BiConsumer<PlayerEntity, ItemStack> openConsumer;
    BiFunction<PlayerEntity, ItemStack, Inventory> bundleInvGetter;
    CanBundleInsertItemFunction canBundleInsertItem;

    public boolean supportsBundleing = false;
    public boolean ignoreSingleStackCheck = false;
    public boolean canOpenInHand = true;

    public QuickShulkerData() {

    }

    public QuickShulkerData(BiConsumer<PlayerEntity, ItemStack> openConsumer, Boolean supportsBundleing) {
        this.openConsumer = openConsumer;
        this.supportsBundleing = supportsBundleing;
    }

    public QuickShulkerData(BiConsumer<PlayerEntity, ItemStack> openConsumer, Boolean supportsBundleing, Boolean ignoreSingleStackCheck) {
        this.openConsumer = openConsumer;
        this.supportsBundleing = supportsBundleing;
        this.ignoreSingleStackCheck = ignoreSingleStackCheck;
    }

    public Inventory getInventory(PlayerEntity player, ItemStack stack) {
        if (bundleInvGetter != null) return bundleInvGetter.apply(player, stack);
        return ShulkerUtils.getInventoryFromShulker(stack);
    }

    public boolean canBundleInsertItem(PlayerEntity player, Inventory inventory, ItemStack hostStack, ItemStack insertStack) {
        if (canBundleInsertItem != null)
            return canBundleInsertItem.canBundleInsertItem(player, inventory, hostStack, insertStack);
        return !ShulkerUtils.isShulkerItem(insertStack);
    }

    public static class QuickEnderData extends QuickShulkerData {
        public QuickEnderData() {
            super();
            canBundleInsertItem = CanBundleInsertItemFunction.ALWAYS;
        }

        public QuickEnderData(BiConsumer<PlayerEntity, ItemStack> openConsumer, Boolean supportsBundleing) {
            super(openConsumer, supportsBundleing);
            canBundleInsertItem = CanBundleInsertItemFunction.ALWAYS;
        }

        public QuickEnderData(BiConsumer<PlayerEntity, ItemStack> openConsumer, Boolean supportsBundleing, Boolean ignoreSingleStackCheck) {
            super(openConsumer, supportsBundleing, ignoreSingleStackCheck);
            canBundleInsertItem = CanBundleInsertItemFunction.ALWAYS;
        }

        public Inventory getInventory(PlayerEntity player, ItemStack stack) {
            if (!QuickShulkerMod.getConfig().quickEChest)
                return null;
            return player.getEnderChestInventory();
        }
    }
}