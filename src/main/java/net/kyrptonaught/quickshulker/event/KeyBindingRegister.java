/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.quickshulker.event;

//#if MC >= 26.1
//$$ import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
//#else
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
//#endif
import net.kyrptonaught.kyrptconfig.keybinding.DisplayOnlyKeyBind;
import net.kyrptonaught.quickshulker.QuickShulkerMod;
//#if MC >= 1.21.11
//$$ import net.minecraft.resources.Identifier;
//#endif
//#if MC >= 1.21.10
//$$ import net.minecraft.client.KeyMapping;
//$$ import net.minecraft.resources.ResourceLocation;
//#endif

public class KeyBindingRegister {
    //#if MC >= 1.21.11
    //$$ public static final KeyMapping.Category MAIN = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(QuickShulkerMod.MOD_ID, "main"));
    //#elseif MC >= 1.21.10
    //$$ public static final KeyMapping.Category MAIN = KeyMapping.Category.register(ResourceLocation.fromNamespaceAndPath(QuickShulkerMod.MOD_ID, "main"));
    //#else
    public static final String MAIN = "key.categories.quickshulker";
    //#endif

    public static void register(){
        //#if MC >= 26.1
        //$$ KeyMappingHelper.registerKeyMapping(new DisplayOnlyKeyBind(
        //#else
        KeyBindingHelper.registerKeyBinding(new DisplayOnlyKeyBind(
        //#endif
                "key.quickshulker.config.openSettingGui",
                MAIN,
                QuickShulkerMod.getConfig().openSettingGui,
                setKey -> QuickShulkerMod.config.save()
        ));
        //#if MC >= 26.1
        //$$ KeyMappingHelper.registerKeyMapping(new DisplayOnlyKeyBind(
        //#else
        KeyBindingHelper.registerKeyBinding(new DisplayOnlyKeyBind(
        //#endif
                "key.quickshulker.config.keybinding",
                MAIN,
                QuickShulkerMod.getConfig().keybinding,
                setKey -> QuickShulkerMod.config.save()
        ));
    }
}
