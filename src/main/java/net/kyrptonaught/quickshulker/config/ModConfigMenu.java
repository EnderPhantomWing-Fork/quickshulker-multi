/*
 * This file is part of the Quick Shulker Multi project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2026  Fallen_Breath and contributors
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

package net.kyrptonaught.quickshulker.config;

import net.kyrptonaught.kyrptconfig.config.screen.ConfigScreen;
import net.kyrptonaught.kyrptconfig.config.screen.ConfigSection;
import net.kyrptonaught.kyrptconfig.config.screen.items.BooleanItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.KeybindItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.SubItem;
import net.kyrptonaught.quickshulker.QuickShulkerMod;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfigMenu {
    public static Screen getModConfigMenu(Screen screen){
        ConfigOptions options = QuickShulkerMod.getConfig();

        ConfigScreen configScreen = new ConfigScreen(screen, Text.translatable("key.quickshulker.config.category.title"));
        configScreen.setSavingEvent(() -> {
            QuickShulkerMod.config.save();
        });
        ConfigSection activationSection = new ConfigSection(configScreen, Text.translatable("key.quickshulker.config.category.activation"));
        activationSection.addConfigItem(new KeybindItem(Text.translatable("key.quickshulker.config.keybinding"), options.keybinding.rawKey, ConfigOptions.defualtKeybind).setSaveConsumer(value -> options.keybinding.setRaw(value)));
        activationSection.addConfigItem(new KeybindItem(Text.translatable("key.quickshulker.config.openSettingGui"), options.openSettingGui.rawKey, options.openSettingGui.defaultKey).setSaveConsumer(value -> options.openSettingGui.setRaw(value)));
        activationSection.addConfigItem(new BooleanItem(Text.translatable("key.quickshulker.config.keybind"), options.keybind, true).setSaveConsumer(value -> options.keybind = value));
        activationSection.addConfigItem(new BooleanItem(Text.translatable("key.quickshulker.config.rightClick"), options.rightClickToOpen, true).setSaveConsumer(value -> options.rightClickToOpen = value));
        activationSection.addConfigItem(new BooleanItem(Text.translatable("key.quickshulker.config.keybindInInv"), options.keybingInInv, true).setSaveConsumer(value -> options.keybingInInv = value));
        activationSection.addConfigItem(new BooleanItem(Text.translatable("key.quickshulker.config.rightClickInInv"), options.rightClickInv, true).setSaveConsumer(value -> options.rightClickInv = value));

        ConfigSection optionsSection = new ConfigSection(configScreen, Text.translatable("key.quickshulker.config.category.options"));
        optionsSection.addConfigItem(new BooleanItem(Text.translatable("key.quickshulker.config.rightClickClose"), options.rightClickClose, false).setSaveConsumer(value -> options.rightClickClose = value));

        SubItem subItem = (SubItem) optionsSection.addConfigItem(new SubItem(Text.translatable("key.quickshulker.config.category.bundleing"), true));
        subItem.addConfigItem(new BooleanItem(Text.translatable("key.quickshulker.config.supportsBundlingInsert"), options.supportsBundlingInsert, true).setSaveConsumer(value -> options.supportsBundlingInsert = value));
        subItem.addConfigItem(new BooleanItem(Text.translatable("key.quickshulker.config.supportsBundlingPickup"), options.supportsBundlingPickup, true).setSaveConsumer(value -> options.supportsBundlingPickup = value));
        subItem.addConfigItem(new BooleanItem(Text.translatable("key.quickshulker.config.supportsBundlingTransfer"), options.supportsBundlingTransfer, true).setSaveConsumer(value -> options.supportsBundlingTransfer = value));
        subItem.addConfigItem(new BooleanItem(Text.translatable("key.quickshulker.config.supportsBundlingExtract"), options.supportsBundlingExtract, true).setSaveConsumer(value -> options.supportsBundlingExtract = value));
        subItem.addConfigItem(new BooleanItem(Text.translatable("key.quickshulker.config.supportsMouseDragged"), options.supportsMouseDragged, true).setSaveConsumer(value -> options.supportsMouseDragged = value));

        ConfigSection enabledSection = new ConfigSection(configScreen, Text.translatable("key.quickshulker.config.category.enabled"));
        enabledSection.addConfigItem(new BooleanItem(Text.translatable("key.quickshulker.config.quickShulkerBox"), options.quickShulkerBox, true).setSaveConsumer(value -> options.quickShulkerBox = value).setRequiresRestart());
        enabledSection.addConfigItem(new BooleanItem(Text.translatable("key.quickshulker.config.quickCraftingTable"), options.quickCraftingTables, true).setSaveConsumer(value -> options.quickCraftingTables = value).setRequiresRestart());
        enabledSection.addConfigItem(new BooleanItem(Text.translatable("key.quickshulker.config.quickStonecutter"), options.quickStonecutter, true).setSaveConsumer(value -> options.quickStonecutter = value).setRequiresRestart());
        enabledSection.addConfigItem(new BooleanItem(Text.translatable("key.quickshulker.config.quickEChest"), options.quickEChest, true).setSaveConsumer(value -> options.quickEChest = value).setRequiresRestart());

        return configScreen;
    }
}
