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

import net.kyrptonaught.jankson.JsonElement;
import net.kyrptonaught.jankson.api.DeserializationException;
import net.kyrptonaught.jankson.impl.MarshallerImpl;

public class CustomMarshaller extends MarshallerImpl {

    public JsonElement serialize(Object obj) {
        if (obj instanceof CustomSerializable customSerializable) {
            return customSerializable.toJson(this);
        }
        return super.serialize(obj);
    }

    public JsonElement serializeNonCustom(Object obj) {
        return super.serialize(obj);
    }

    public CustomSerializable marshallCustomSerializable(Class<CustomSerializable> clazz, CustomSerializable originalObject, JsonElement elem) throws DeserializationException {
        return originalObject.fromJson(this, elem, clazz);
    }

    public <T> T marshallNonCustom(Class<T> clazz, JsonElement elem, boolean failFast) throws DeserializationException {
        return super.marshall(clazz, elem, failFast);
    }
}
