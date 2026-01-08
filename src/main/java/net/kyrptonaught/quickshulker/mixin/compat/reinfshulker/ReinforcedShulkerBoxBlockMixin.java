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

import atonkish.reinfcore.util.ReinforcingMaterial;
import atonkish.reinfshulker.block.ReinforcedShulkerBoxBlock;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.kyrptonaught.quickshulker.compat.ModIds;
import net.kyrptonaught.shulkerutils.UpgradableShulker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Restriction(require = @Condition(ModIds.reinfshulker))
@Mixin(ReinforcedShulkerBoxBlock.class)
public abstract class ReinforcedShulkerBoxBlockMixin implements UpgradableShulker {

    @Shadow(remap = false)
    public abstract ReinforcingMaterial getMaterial();

    @Override
    public int getInventorySize() {
        return this.getMaterial().getSize();
    }
}
