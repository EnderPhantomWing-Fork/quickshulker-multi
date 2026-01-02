package net.kyrptonaught.quickshulker.mixin.compat.reinfshulker;

//#if MC >= 1.21.11
//$$ import net.minecraft.server.MinecraftServer;
//$$ import org.spongepowered.asm.mixin.Mixin;
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//$$ @Mixin(MinecraftServer.class)
//$$ public abstract class RenifShulkerMixin {
//$$     @Inject(method = "runServer", at = @At("HEAD"))
//$$     private void onRun(CallbackInfo ci) {
//$$         System.err.println("1.21.11+ Reinforced Shulker Box is not yet supported.");
//$$     }
//$$ }
//#else
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
public abstract class RenifShulkerMixin implements UpgradableShulker {

    @Shadow(remap = false)
    public abstract ReinforcingMaterial getMaterial();

    @Override
    public int getInventorySize() {
        return this.getMaterial().getSize();
    }
}
//#endif
