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

import net.kyrptonaught.jankson.JsonElement;
import net.kyrptonaught.jankson.JsonPrimitive;
import net.kyrptonaught.kyrptconfig.config.CustomMarshaller;
import net.kyrptonaught.kyrptconfig.config.CustomSerializable;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;

import java.util.Optional;

public class CustomKeyBinding implements CustomSerializable {
    public boolean unknownIsActivated = false;
    public String rawKey = "";
    public String defaultKey = "";
    public InputConstants.Key parsedKey;
    public boolean doParseKey = true;
    private final String MOD_ID;

    public CustomKeyBinding(String MOD_ID) {
        this.MOD_ID = MOD_ID;
    }

    public CustomKeyBinding(String MOD_ID, boolean unknownIsActivated) {
        this.unknownIsActivated = unknownIsActivated;
        this.MOD_ID = MOD_ID;
    }

    public static CustomKeyBinding configDefault(String MOD_ID, String defaultKey) {
        CustomKeyBinding customKeyBinding = new CustomKeyBinding(MOD_ID).setRaw(defaultKey);
        customKeyBinding.defaultKey = defaultKey;
        return customKeyBinding;
    }

    public CustomKeyBinding setRaw(String key) {
        rawKey = key;
        doParseKey = true;
        holding = false;
        return this;
    }

    boolean holding = false;

    public boolean wasPressed() {
        boolean pressed = isKeybindPressed();
        if (!holding) {
            holding = pressed;
            return pressed;
        }
        if (!pressed)
            holding = false;
        return false;
    }

    private void parseKeycode() {
        if (doParseKey) {
            parsedKey = getKeybinding().orElse(null);
            doParseKey = false;
        }
    }

    public boolean isKeybindPressed() {
        parseKeycode();
        if (parsedKey == null) // Invalid key
            return false;
        if (parsedKey == InputConstants.UNKNOWN)
            return unknownIsActivated; // Always pressed for empty or explicitly "key.keyboard.unknown"
        boolean pressed;
        if (parsedKey.getType() == InputConstants.Type.MOUSE)
            //#if MC >= 1.21.10
            //$$ pressed = GLFW.glfwGetMouseButton(Minecraft.getInstance().getWindow().handle(), parsedKey.getValue()) == 1;
            //#else
            pressed = GLFW.glfwGetMouseButton(Minecraft.getInstance().getWindow().getWindow(), parsedKey.getValue()) == 1;
            //#endif
        else
            //#if MC >= 1.21.10
            //$$ pressed = GLFW.glfwGetKey(Minecraft.getInstance().getWindow().handle(), parsedKey.getValue()) == 1;
            //#else
            pressed = GLFW.glfwGetKey(Minecraft.getInstance().getWindow().getWindow(), parsedKey.getValue()) == 1;
            //#endif
        return pressed;
    }

    public boolean matches(int keyCode, InputConstants.Type type) {
        parseKeycode();
        if (parsedKey == null) return false;
        return parsedKey.getType() == type && parsedKey.getValue() == keyCode;
    }

    public Optional<InputConstants.Key> getKeybinding() {
        if (rawKey.isEmpty())
            return Optional.of(InputConstants.UNKNOWN);
        try {
            return Optional.of(InputConstants.getKey(rawKey));
        } catch (IllegalArgumentException e) {
            System.out.println(MOD_ID + ": unknown key entered");
            return Optional.empty();
        }
    }

    public InputConstants.Key getDefaultKey() {
        if (defaultKey == null || defaultKey.isEmpty())
            return InputConstants.UNKNOWN;
        try {
            return InputConstants.getKey(defaultKey);
        } catch (IllegalArgumentException e) {
            System.out.println(MOD_ID + ": unknown default key entered");
            return InputConstants.UNKNOWN;
        }
    }

    @Override
    public JsonElement toJson(CustomMarshaller m) {
        return new JsonPrimitive(this.rawKey);
    }

    @Override
    public CustomSerializable fromJson(CustomMarshaller m, JsonElement obj, Class<CustomSerializable> clazz) {
        if (obj instanceof JsonPrimitive string)
            setRaw(string.asString());
        return this;
    }
}
