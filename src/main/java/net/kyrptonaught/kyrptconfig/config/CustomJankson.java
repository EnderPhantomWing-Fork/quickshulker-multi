/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.kyrptconfig.config;

import net.kyrptonaught.jankson.Jankson;

import java.lang.reflect.Field;

public class CustomJankson {
    public static Jankson.Builder customJanksonBuilder() {
        return new Jankson.Builder(true);
    }

    public static Boolean shouldSerializeField(Object t, Field field) {
        if (t instanceof CustomSerializable customSerializable)
            return customSerializable.shouldSerializeField(field);
        return true;
    }
}
