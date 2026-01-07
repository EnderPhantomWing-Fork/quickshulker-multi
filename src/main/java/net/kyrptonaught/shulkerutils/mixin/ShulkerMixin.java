package net.kyrptonaught.shulkerutils.mixin;

import net.kyrptonaught.shulkerutils.UpgradableShulker;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import org.spongepowered.asm.mixin.Mixin;

// TODO(Ravel): can not resolve target class ShulkerBoxBlock
@Mixin(ShulkerBoxBlock.class)
public class ShulkerMixin implements UpgradableShulker {

    @Override
    public int getInventorySize() {
        return 27;
    }

}
