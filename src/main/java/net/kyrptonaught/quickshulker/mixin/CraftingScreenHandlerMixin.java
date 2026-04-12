/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.quickshulker.mixin;

import net.kyrptonaught.quickshulker.api.ItemInventoryContainer;
import net.kyrptonaught.quickshulker.api.Util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.GrindstoneMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.StonecutterMenu;
import net.minecraft.world.inventory.LoomMenu;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = {CraftingMenu.class, StonecutterMenu.class, ItemCombinerMenu.class, GrindstoneMenu.class, LoomMenu.class})
public abstract class CraftingScreenHandlerMixin extends AbstractContainerMenu {

    protected CraftingScreenHandlerMixin(@Nullable MenuType<?> type, int syncId) {
        super(type, syncId);
    }

    @Inject(method = "stillValid", at = @At("HEAD"), cancellable = true)
    public void overrideCanUse(Player player, CallbackInfoReturnable<Boolean> cir) {
        if (((ItemInventoryContainer) this).hasItem()) {
            ItemStack stack = player.getInventory().getItem(((ItemInventoryContainer) this).QS$getUsedSlotInPlayerInv());
            if (Util.isOpenableItem(stack))
                cir.setReturnValue(true);
        }
    }
}
