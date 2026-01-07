package net.kyrptonaught.quickshulker.compat.reinfshulker;

import atonkish.reinfcore.screen.ReinforcedStorageScreenHandler;
import atonkish.reinfcore.util.ReinforcingMaterial;
import atonkish.reinfshulker.block.ReinforcedShulkerBoxBlock;
import atonkish.reinfshulker.block.entity.ModBlockEntityType;
import net.kyrptonaught.quickshulker.api.ItemStackInventory;
import net.kyrptonaught.quickshulker.api.QuickOpenableRegistry;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.network.chat.Component;

import java.util.function.BiConsumer;

public class ReinfshulkerOpenableRegistry{

    private static final BiConsumer<Player, ItemStack> REINFORCED_SHULKER_BOX_CONSUMER = (Player player, ItemStack stack) -> {
        ReinforcedShulkerBoxBlock block = (ReinforcedShulkerBoxBlock) ((BlockItem) stack.getItem()).getBlock();
        ReinforcingMaterial material = block.getMaterial();
        ItemStackInventory inventory = new ItemStackInventory(stack, material.getSize());
        String namespace = BlockEntityType.getKey(ModBlockEntityType.REINFORCED_SHULKER_BOX_MAP.get(material)).getNamespace();

        MenuConstructor screenHandlerFactory = (int syncId, Inventory playerInventory, Player playerEntity) ->
                ReinforcedStorageScreenHandler.createShulkerBoxScreen(material, syncId, playerInventory, inventory);
        Component text = stack.getComponents().has(
                DataComponents.CUSTOM_NAME) ? stack.getHoverName() : Component.translatable("container." + namespace + "." + material.getName() + "ShulkerBox"
        );

        player.openMenu(new SimpleMenuProvider(screenHandlerFactory, text));
    };

    public static void registerProviders() {
        new QuickOpenableRegistry.Builder()
                .setItem(ReinforcedShulkerBoxBlock.class)
                .supportsBundleing(true)
                .setOpenAction(REINFORCED_SHULKER_BOX_CONSUMER)
                .register();
    }
}
