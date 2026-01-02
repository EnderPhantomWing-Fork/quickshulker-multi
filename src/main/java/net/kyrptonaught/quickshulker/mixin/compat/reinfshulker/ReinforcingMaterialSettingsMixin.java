package net.kyrptonaught.quickshulker.mixin.compat.reinfshulker;

//#if MC >= 1.21.11
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
//$$         System.err.println("1.21.11+ Reinforced Shulker Box is not yet supported.<2>");
//$$     }
//$$ }
//#else
import atonkish.reinfshulker.util.ReinforcingMaterialSettings;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.kyrptonaught.quickshulker.compat.ModIds;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Restriction(require = @Condition(ModIds.reinfshulker))
@Mixin(ReinforcingMaterialSettings.class)
public class ReinforcingMaterialSettingsMixin {

    @ModifyVariable(method = "<init>", at = @At("HEAD"), argsOnly = true)
    private static Item.Settings itemSettings(Item.Settings itemSettings){
            return itemSettings.component(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT);
    }
}
//#endif
