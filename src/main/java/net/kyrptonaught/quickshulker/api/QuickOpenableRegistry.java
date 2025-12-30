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

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class QuickOpenableRegistry {
    private static final HashMap<Class<? extends ItemConvertible>, QuickShulkerData> quickies = new HashMap<>();

    public static QuickShulkerData getQuickie(ItemConvertible item) {
        if (item instanceof BlockItem) {
            if (quickies.containsKey(((BlockItem) item).getBlock().getClass()))
                return quickies.get(((BlockItem) item).getBlock().getClass());
        }
        return quickies.get(item.getClass());
    }

    public static void register(Class<? extends ItemConvertible> quickItem, QuickShulkerData quickShulkerData) {
        quickies.put(quickItem, quickShulkerData);
    }

    @Deprecated
    public static void register(Class<? extends ItemConvertible> quickItem, Boolean requiresSingularStack, Boolean supportsBundleing, BiConsumer<PlayerEntity, ItemStack> consumer) {
        register(quickItem, new QuickShulkerData(consumer, supportsBundleing));
    }

    @Deprecated
    public static void register(Class<? extends ItemConvertible> quickItem, Boolean supportsBundleing, BiConsumer<PlayerEntity, ItemStack> consumer) {
        register(quickItem, new QuickShulkerData(consumer, supportsBundleing));
    }

    @Deprecated
    public static void register(Class<? extends ItemConvertible> quickItem, BiConsumer<PlayerEntity, ItemStack> consumer) {
        register(quickItem, new QuickShulkerData(consumer, false));
    }

    @SafeVarargs
    @Deprecated
    public static void register(BiConsumer<PlayerEntity, ItemStack> consumer, Class<? extends ItemConvertible>... quickItems) {
        for (Class<? extends ItemConvertible> block : quickItems) {
            register(block, consumer);
        }
    }

    public static class Builder {
        private final List<Class<? extends ItemConvertible>> quickItems = new ArrayList<>();
        private final QuickShulkerData qsdata;

        public Builder() {
            qsdata = new QuickShulkerData();
        }

        public Builder(QuickShulkerData qsdata) {
            this.qsdata = qsdata;
        }

        public void register() {
            for (Class<? extends ItemConvertible> quickItem : quickItems)
                QuickOpenableRegistry.register(quickItem, qsdata);
        }

        @SafeVarargs
        public final Builder setItem(Class<? extends ItemConvertible>... quickItems) {
            this.quickItems.addAll(List.of(quickItems));
            return this;
        }

        public Builder setOpenAction(BiConsumer<PlayerEntity, ItemStack> openAction) {
            qsdata.openConsumer = openAction;
            return this;
        }

        public Builder supportsBundleing(Boolean supportsBundleing) {
            qsdata.supportsBundleing = supportsBundleing;
            return this;
        }

        public Builder getBundleInv(BiFunction<PlayerEntity, ItemStack, Inventory> getBundleInv) {
            qsdata.bundleInvGetter = getBundleInv;
            return this;
        }

        public Builder canBundleInsertItem(CanBundleInsertItemFunction canBundleInsertItem) {
            qsdata.canBundleInsertItem = canBundleInsertItem;
            return this;
        }

        public Builder canOpenInHand(boolean canOpenInHand) {
            qsdata.canOpenInHand = canOpenInHand;
            return this;
        }

        public Builder ignoreSingleStackCheck(Boolean ignoreSingleStackCheck) {
            qsdata.ignoreSingleStackCheck = ignoreSingleStackCheck;
            return this;
        }
    }
}