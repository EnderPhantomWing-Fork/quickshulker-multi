/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.kyrptconfig.keybinding;

import net.minecraft.client.KeyMapping;
import com.mojang.blaze3d.platform.InputConstants;
import org.jetbrains.annotations.NotNull;
//#if MC >= 1.21.10
//$$ import net.minecraft.resources.ResourceLocation;
//#endif

import java.util.function.Consumer;

public class DisplayOnlyKeyBind extends KeyMapping {
    private final Consumer<InputConstants.Key> keySet;
    private CustomKeyBinding customKeyBinding;

    //#if MC >= 1.21.10
    //$$ public DisplayOnlyKeyBind(String translationKey, InputConstants.Type type, int code, KeyMapping.Category category) {
    //#else
    public DisplayOnlyKeyBind(String translationKey, InputConstants.Type type, int code, String category) {
    //#endif
        super(translationKey, type, code, category);
        keySet = (boundKey) -> {
        };
    }

    //#if MC >= 1.21.10
    //$$ public DisplayOnlyKeyBind(String translationKey, KeyMapping.Category category, CustomKeyBinding customKeyBinding, Consumer<InputConstants.Key> keySet) {
    //#else
    public DisplayOnlyKeyBind(String translationKey, String category, CustomKeyBinding customKeyBinding, Consumer<InputConstants.Key> keySet) {
    //#endif
        super(translationKey, customKeyBinding.getDefaultKey().getType(), customKeyBinding.getDefaultKey().getValue(), category);
        this.customKeyBinding = customKeyBinding;
        this.keySet = keySet;
        updateSetKey();
    }

    public void setKey(InputConstants.@NotNull Key boundKey) {
        super.setKey(boundKey);
        if (customKeyBinding != null)
            customKeyBinding.setRaw(saveString());
        keySet.accept(boundKey);
    }

    public void updateSetKey() {
        super.setKey(customKeyBinding.getKeybinding().orElse(InputConstants.UNKNOWN));
    }

    @Override
    //#if MC >= 1.21.10
    //$$ public KeyMapping.Category getCategory() {
    //#else
    public @NotNull String getCategory() {
    //#endif
        updateSetKey();
        return super.getCategory();
    }

    @Override
    public @NotNull String getName() {
        updateSetKey();
        return super.getName();
    }

    @Override
    public InputConstants.@NotNull Key getDefaultKey() {
        updateSetKey();
        return super.getDefaultKey();
    }
}
