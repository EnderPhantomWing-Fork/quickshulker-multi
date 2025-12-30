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

    public CustomSerializable marshallCustomSerializable(Class<CustomSerializable> clazz, CustomSerializable origianlObject, JsonElement elem) throws DeserializationException {
        return origianlObject.fromJson(this, elem, clazz);
    }

    public <T> T marshallNonCustom(Class<T> clazz, JsonElement elem, boolean failFast) throws DeserializationException {
        return super.marshall(clazz, elem, failFast);
    }
}
