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

package net.kyrptonaught.quickshulker.compat.reinfshulker;

import atonkish.reinfcore.screen.ReinforcedStorageScreenHandler;
import atonkish.reinfcore.util.ReinforcingMaterial;
import atonkish.reinfshulker.block.ReinforcedShulkerBoxBlock;
import atonkish.reinfshulker.block.entity.ModBlockEntityType;
import net.kyrptonaught.quickshulker.api.ItemStackInventory;
import net.kyrptonaught.quickshulker.api.QuickOpenableRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;

import java.util.function.BiConsumer;

public class ReinfshulkerOpenableRegistry {

    private static final BiConsumer<PlayerEntity, ItemStack> REINFORCED_SHULKER_BOX_CONSUMER = (PlayerEntity player, ItemStack stack) -> {
        ReinforcedShulkerBoxBlock block = (ReinforcedShulkerBoxBlock) ((BlockItem) stack.getItem()).getBlock();
        ReinforcingMaterial material = block.getMaterial();
        ItemStackInventory inventory = new ItemStackInventory(stack, material.getSize());
        String namespace = BlockEntityType.getId(ModBlockEntityType.REINFORCED_SHULKER_BOX_MAP.get(material)).getNamespace();

        ScreenHandlerFactory screenHandlerFactory = (int syncId, PlayerInventory playerInventory, PlayerEntity playerEntity) ->
                ReinforcedStorageScreenHandler.createShulkerBoxScreen(material, syncId, playerInventory, inventory);
        Text text = stack.getComponents().contains(
                DataComponentTypes.CUSTOM_NAME) ? stack.getName() : Text.translatable("container." + namespace + "." + material.getName() + "ShulkerBox"
        );

        player.openHandledScreen(new SimpleNamedScreenHandlerFactory(screenHandlerFactory, text));
    };

    public static void registerProviders() {
        new QuickOpenableRegistry.Builder()
                .setItem(ReinforcedShulkerBoxBlock.class)
                .supportsBundleing(true)
                .setOpenAction(REINFORCED_SHULKER_BOX_CONSUMER)
                .register();
    }
}
