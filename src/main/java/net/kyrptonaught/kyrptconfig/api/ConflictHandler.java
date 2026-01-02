/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
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
