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
