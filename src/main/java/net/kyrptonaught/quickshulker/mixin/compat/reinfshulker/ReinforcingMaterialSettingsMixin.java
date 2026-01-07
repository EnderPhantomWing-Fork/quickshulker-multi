package net.kyrptonaught.quickshulker.mixin.compat.reinfshulker;

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

    @ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true)
    private static Item.Properties itemSettings(Item.Properties itemSettings){
            return itemSettings.component(DataComponents.CONTAINER, ItemContainerContents.EMPTY);
    }

}
