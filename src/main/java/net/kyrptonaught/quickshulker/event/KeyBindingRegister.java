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

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.kyrptonaught.kyrptconfig.keybinding.DisplayOnlyKeyBind;
import net.kyrptonaught.quickshulker.QuickShulkerMod;
//#if MC >= 1.21.10
//$$ import net.minecraft.client.option.KeyBinding;
//$$ import net.minecraft.util.Identifier;
//#else
//#endif

public class KeyBindingRegister {
    //#if MC >= 1.21.10
    //$$  public static final KeyBinding.Category MAIN = KeyBinding.Category.create(Identifier.of(QuickShulkerMod.MOD_ID));
    //
    //$$  public static void register(){
    //$$      KeyBindingHelper.registerKeyBinding(new DisplayOnlyKeyBind(
    //$$              "key.quickshulker.config.openSettingGui",
    //$$              MAIN,
    //$$              QuickShulkerMod.getConfig().openSettingGui,
    //$$              setKey -> QuickShulkerMod.config.save()
    //$$      ));
    //$$      KeyBindingHelper.registerKeyBinding(new DisplayOnlyKeyBind(
    //$$              "key.quickshulker.config.keybinding",
    //$$              MAIN,
    //$$              QuickShulkerMod.getConfig().keybinding,
    //$$              setKey -> QuickShulkerMod.config.save()
    //$$      ));
    //$$  }
    //#else
    public static final String MAIN = "key.categories.quickshulker";

    public static void register(){
        KeyBindingHelper.registerKeyBinding(new DisplayOnlyKeyBind(
                "key.quickshulker.config.openSettingGui",
                MAIN,
                QuickShulkerMod.getConfig().openSettingGui,
                setKey -> QuickShulkerMod.config.save()
        ));
        KeyBindingHelper.registerKeyBinding(new DisplayOnlyKeyBind(
                "key.quickshulker.config.keybinding",
                MAIN,
                QuickShulkerMod.getConfig().keybinding,
                setKey -> QuickShulkerMod.config.save()
        ));
    }
    //#endif
}
