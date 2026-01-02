/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.jankson;

import java.io.IOException;
import java.io.Writer;

public class JsonNull extends JsonElement {
    public static final JsonNull INSTANCE = new JsonNull();

    private JsonNull() {
    }

    public String toString() {
        return "null";
    }

    @Override
    public boolean equals(Object other) {
        return other == JsonNull.INSTANCE;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public String toJson(boolean comments, boolean newlines, int depth) {
        return "null";
    }

    @Override
    public void toJson(Writer writer, JsonGrammar grammar, int depth) throws IOException {
        writer.write("null");
    }

    //IMPLEMENTATION for Cloneable
    @Override
    public JsonNull clone() {
        return this; //Technically violates the contract for Cloneable, but this is a singleton
    }
}
