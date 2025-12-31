/*
 * This file is part of the Quick Shulker Multi project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2025  Fallen_Breath and contributors
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
    //$$  public static final KeyBinding.Category MAIN = KeyBinding.Category.create(Identifier.of(QuickShulkerMod.MOD_ID, "main"));
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
