/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.quickshulker.mixin.compat.reinfshulker;

//#if MC >= 26.1
//$$ import net.minecraft.server.MinecraftServer;
//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//$$ @Mixin(MinecraftServer.class)
//$$ public abstract class ReinforcingMaterialSettingsMixin {
//$$     @Inject(method = "runServer", at = @At("HEAD"))
//$$     private void onRun(CallbackInfo ci) {
//$$         System.err.println("26.1+ Reinforced Shulker Box is not yet supported.");
//$$     }
//$$ }
//#else

import atonkish.reinfshulker.util.ReinforcingMaterialSettings;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.kyrptonaught.quickshulker.compat.ModIds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.item.Item;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Restriction(require = @Condition(ModIds.reinfshulker))
@Mixin(ReinforcingMaterialSettings.class)
public class ReinforcingMaterialSettingsMixin {

    @ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true, name = "itemSettings")
    private static Item.Properties itemSettings(Item.Properties itemSettings) {
        return itemSettings.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY);
    }
}
//#endif
