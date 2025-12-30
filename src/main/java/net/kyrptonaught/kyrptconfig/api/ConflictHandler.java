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

package net.kyrptonaught.kyrptconfig.api;

import net.kyrptonaught.kyrptconfig.config.screen.items.ConfigItem;
import net.kyrptonaught.kyrptconfig.config.screen.items.KeybindItem;

import java.util.ArrayList;

public class ConflictHandler {
    public static final ArrayList<KeybindItem> CUSTOM_KEYBIND_ITEMS = new ArrayList<>();

    public static void updateMap(ConfigItem<String> item){
        if(item instanceof KeybindItem customItem){
            for(KeybindItem keybindItem : CUSTOM_KEYBIND_ITEMS){
                if(keybindItem.getTitleText().equals(item.getTitleText())){
                    CUSTOM_KEYBIND_ITEMS.remove(keybindItem);
                    break;
                }
            }
            CUSTOM_KEYBIND_ITEMS.add(customItem);
            updateCustomConflicts();
        }
    }

    public static void updateCustomConflicts(){
        for(KeybindItem keybindItem : CUSTOM_KEYBIND_ITEMS){
            keybindItem.updateMessage();
        }
    }
}
