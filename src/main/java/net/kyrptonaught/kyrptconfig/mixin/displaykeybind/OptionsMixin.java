/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.kyrptconfig.mixin.displaykeybind;

import net.kyrptonaught.kyrptconfig.keybinding.DisplayOnlyKeyBind;
import net.kyrptonaught.kyrptconfig.keybinding.SpoofedKeysHelper;
import net.minecraft.client.Options;
import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public class OptionsMixin {

    @Shadow
    @Final
    public KeyMapping[] keyMappings;


    @Inject(method = "processOptions", at = @At(value = "HEAD"))
    public void genSpoofedKeyBindList(Options.FieldAccess visitor, CallbackInfo ci) {
        SpoofedKeysHelper.spoofed_Keys.clear();
        for (KeyMapping keyBinding : this.keyMappings) {
            if (keyBinding instanceof DisplayOnlyKeyBind)
                SpoofedKeysHelper.spoofed_Keys.add("key_" + keyBinding.getName());
        }
    }
}
