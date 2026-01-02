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

import net.kyrptonaught.jankson.Jankson;
import net.kyrptonaught.jankson.JsonArray;
import net.kyrptonaught.jankson.api.SyntaxError;

public class ArrayParserContext implements ParserContext<JsonArray> {
    private final JsonArray result = new JsonArray();
    private boolean foundClosingBrace = false;
    //private String comment = null;

    /**
     * Assumes the opening brace has already been consumed!
     */
    public ArrayParserContext() {

    }

    @Override
    public boolean consume(int codePoint, Jankson loader) throws SyntaxError {
        result.setMarshaller(loader.getMarshaller());
        if (foundClosingBrace) return false;
        if (Character.isWhitespace(codePoint) || codePoint == ',') return true;

        if (codePoint == ']') {
            foundClosingBrace = true;
            return true;
        }

        loader.push(new ElementParserContext(), (it) -> {
            if (it.getElement() != null) {
                result.add(it.getElement(), it.getComment());
            } else {
                String existing = result.getComment(result.size() - 1);
                if (existing == null) existing = "";
                String combined = existing + "\n" + it.getComment();
                result.setComment(result.size() - 1, combined);
            }
        });
        return false;
    }

    @Override
    public void eof() throws SyntaxError {
        if (foundClosingBrace) return;
        throw new SyntaxError("Unexpected end-of-file in the middle of a list! Are you missing a ']'?");
    }

    @Override
    public boolean isComplete() {
        return foundClosingBrace;
    }

    @Override
    public JsonArray getResult() throws SyntaxError {
        return result;
    }

}
