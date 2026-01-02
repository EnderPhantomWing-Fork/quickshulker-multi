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
import net.kyrptonaught.jankson.JsonPrimitive;
import net.kyrptonaught.jankson.api.SyntaxError;

public class TokenParserContext implements ParserContext<JsonPrimitive> {
    private String token = "";
    private boolean complete = false;

    public TokenParserContext(int firstCodePoint) {
        token += (char) firstCodePoint;
    }

    @Override
    public boolean consume(int codePoint, Jankson loader) throws SyntaxError {
        if (complete) return false;

        if (codePoint == '~' || Character.isUnicodeIdentifierPart(codePoint)) {

            if (codePoint < 0xFFFF) {
                token += ((char) codePoint);
                return true;
            } else {
                //Construct a high and low surrogate pair for this code point
                //TODO: Finish implementing
                int temp = codePoint - 0x10000;
                int highSurrogate = (temp >>> 10) + 0xD800;
                int lowSurrogate = (temp & 0b11_1111_1111) + 0xDC00;

                token += (char) highSurrogate;
                token += (char) lowSurrogate;

                return true;
            }

        } else {
            complete = true;
            return false;
        }
    }

    @Override
    public void eof() throws SyntaxError {
        complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }

    @Override
    public JsonPrimitive getResult() throws SyntaxError {
        return JsonPrimitive.of(token);
    }
}
