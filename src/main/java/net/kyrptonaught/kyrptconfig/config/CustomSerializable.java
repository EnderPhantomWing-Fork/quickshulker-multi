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

import java.lang.reflect.Field;

public interface CustomSerializable {

    default JsonElement toJson(CustomMarshaller m) {
        return m.serializeNonCustom(this);
    }

    default CustomSerializable fromJson(CustomMarshaller m, JsonElement obj, Class<CustomSerializable> clazz) throws DeserializationException {
        return m.marshallNonCustom(clazz, obj, false);
    }

    default boolean shouldSerializeField(Field field) {
        return shouldSerializeField(field.getName());
    }

    default boolean shouldSerializeField(String field) {
        return true;
    }
}
