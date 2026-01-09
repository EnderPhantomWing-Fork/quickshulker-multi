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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
//#if MC >= 26.1
//$$ import net.minecraft.world.inventory.ContainerInput;
//#else
import net.minecraft.world.inventory.ClickType;
//#endif
import net.minecraft.core.NonNullList;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(AbstractContainerMenu.class)
public abstract class ContainerMixin implements ItemInventoryContainer {

    int playerInvSlot = -1;

    public int getUsedSlotInPlayerInv() {
        return playerInvSlot;
    }

    public void setUsedSlot(int playerInvSlotID) {
        this.playerInvSlot = playerInvSlotID;
    }

    @Shadow
    @Final
    public NonNullList<Slot> slots;

    @Inject(method = "clicked", at = @At("HEAD"), cancellable = true)
    //#if MC >= 26.1
    //$$ public void QS$onClick(int slotId, int button, ContainerInput actionType, Player player, CallbackInfo ci) {
    //#else
    public void QS$onClick(int slotId, int button, ClickType actionType, Player player, CallbackInfo ci) {
    //#endif
        if (slotId > 0 && slotId < slots.size()) {
            if (hasItem())
                if (slots.get(slotId).container instanceof Inventory && slots.get(slotId).getContainerSlot() == playerInvSlot)
                    ci.cancel();
        }
    }
}