/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.jankson.api;

import net.kyrptonaught.jankson.impl.serializer.InternalDeserializerFunction;

@FunctionalInterface
public interface DeserializerFunction<A, B> extends InternalDeserializerFunction<B> {
    B apply(A a, Marshaller m) throws DeserializationException;

    @SuppressWarnings("unchecked")
    @Override
    default B deserialize(Object a, Marshaller m) throws DeserializationException {
        try {
            return apply((A) a, m);
        } catch (ClassCastException ex) {
            throw new DeserializationException(ex);
        }
    }
}
