/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.jankson.impl;

import net.kyrptonaught.jankson.JsonElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Holds both a JsonElement and its associated comment, and any other relevant data
 */
public class AnnotatedElement {
    protected String comment;
    protected JsonElement elem;

    public AnnotatedElement(@Nonnull JsonElement elem, @Nullable String comment) {
        this.comment = comment;
        this.elem = elem;
    }

    @Nullable
    public String getComment() {
        return comment;
    }

    @Nonnull
    public JsonElement getElement() {
        return elem;
    }
}
