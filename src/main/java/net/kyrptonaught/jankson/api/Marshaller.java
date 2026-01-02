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

import net.kyrptonaught.jankson.JsonElement;

import java.lang.reflect.Type;

public interface Marshaller {
    /**
     * Turns a java object into its JSON intermediate representation.
     */
    JsonElement serialize(Object obj);

    /**
     * Unpacks the provided JsonElement into a new object of type {@code clazz}, making the best
     * effort to unpack all the fields it can. Any fields that cannot be unpacked will be left in
     * the state the initializer and no-arg constructor leaves them in.
     *
     * <p>Note: Consider using {@link #marshallCarefully(Class, JsonElement)} to detect errors first,
     * and then calling this method as a fallback if an error is encountered.
     *
     * @param clazz The class of the object to create and deserialize
     * @param elem  JSON intermediate representation of the data to be unpacked.
     * @param <E>   The type of the object to create and deserialize
     * @return A new object of the provided class that represents the data in the JSON provided.
     */
    <E> E marshall(Class<E> clazz, JsonElement elem);

    /**
     * Unpacks the provided JsonElement into an object of the provided Type, and force-casts it to
     * E.
     *
     * @param type The type to deserialize to
     * @param elem JSON intermediate representation of the data to be unpacked.
     * @param <E>  The type to force-cast to at the end
     * @return A new object of the provided Type that represents the data in the JSON provided.
     */
    <E> E marshall(Type type, JsonElement elem);

    /**
     * Unpacks the provided JsonElement in fail-fast mode. A detailed exception is thrown for any
     * problem encountered during the unpacking process.
     *
     * @param clazz The class of the object to create and deserialize
     * @param elem  JSON intermediate representation of the data to be unpacked.
     * @param <E>   The type of the object to create and deserialize
     * @return A new object of the provided class that represents the data in the JSON provided.
     * @throws DeserializationException if any problems are encountered unpacking the data.
     */
    <E> E marshallCarefully(Class<E> clazz, JsonElement elem) throws DeserializationException;
}
