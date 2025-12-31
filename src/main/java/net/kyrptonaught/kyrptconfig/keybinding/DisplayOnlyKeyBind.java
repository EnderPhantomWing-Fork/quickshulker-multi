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

package net.kyrptonaught.kyrptconfig.keybinding;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.function.Consumer;

public class DisplayOnlyKeyBind extends KeyBinding {
    private CustomKeyBinding customKeyBinding;
    private final Consumer<InputUtil.Key> keySet;

    //#if MC >= 1.21.10
    //$$ public DisplayOnlyKeyBind(String translationKey, InputUtil.Type type, int code, KeyBinding.Category category) {
    //#else
    public DisplayOnlyKeyBind(String translationKey, InputUtil.Type type, int code, String category) {
    //#endif
        super(translationKey, type, code, category);
        keySet = (boundKey) -> {
        };
    }

    //#if MC >= 1.21.10
    //$$ public DisplayOnlyKeyBind(String translationKey, KeyBinding.Category category, CustomKeyBinding customKeyBinding, Consumer<InputUtil.Key> keySet) {
    //#else
    public DisplayOnlyKeyBind(String translationKey, String category, CustomKeyBinding customKeyBinding, Consumer<InputUtil.Key> keySet) {
    //#endif
        super(translationKey, customKeyBinding.getDefaultKey().getCategory(), customKeyBinding.getDefaultKey().getCode(), category);
        this.customKeyBinding = customKeyBinding;
        this.keySet = keySet;
        updateSetKey();
    }

    public void setBoundKey(InputUtil.Key boundKey) {
        super.setBoundKey(boundKey);
        if (customKeyBinding != null)
            customKeyBinding.setRaw(getBoundKeyTranslationKey());
        keySet.accept(boundKey);
    }

    public void updateSetKey() {
        super.setBoundKey(customKeyBinding.getKeybinding().orElse(InputUtil.UNKNOWN_KEY));
    }

    //#if MC >= 1.21.10
    //$$ @Override
    //$$ public KeyBinding.Category getCategory() {
    //$$     updateSetKey();
    //$$     return super.getCategory();
    //$$ }
    //#else
    @Override
    public String getCategory() {
        updateSetKey();
        return super.getCategory();
    }
    //#endif

    @Override
    public String getTranslationKey() {
        updateSetKey();
        return super.getTranslationKey();
    }

    @Override
    public InputUtil.Key getDefaultKey() {
        updateSetKey();
        return super.getDefaultKey();
    }
}
