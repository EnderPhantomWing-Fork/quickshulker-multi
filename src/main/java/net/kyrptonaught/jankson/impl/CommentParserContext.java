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
import net.kyrptonaught.jankson.api.SyntaxError;

public class CommentParserContext implements ParserContext<String> {
    int firstChar = -1;
    int secondChar = -1;

    StringBuilder result = new StringBuilder();

    int prevChar = -1;

    boolean startOfLine = true;
    boolean multiLine = false;
    boolean done = false;

    public CommentParserContext(int codePoint) {
        firstChar = codePoint;
    }

    @Override
    public boolean consume(int codePoint, Jankson loader) throws SyntaxError {
        if (done) return false;

        if (firstChar == -1) {
            if (codePoint != '/' && codePoint != '#') {
                throw new SyntaxError("Was expecting the start of a comment, but found '" + (char) codePoint + "' instead.");
            }
            firstChar = codePoint;
            if (firstChar == '#') multiLine = false;
            return true;
        }

        if (secondChar == -1 && firstChar != '#') {
            secondChar = codePoint;
            if (codePoint == '*') {
                multiLine = true;
                return true;
            } else if (codePoint == '/') {
                multiLine = false;
                return true;
            } else {
                if (Character.isWhitespace(codePoint)) {
                    throw new SyntaxError("Was expecting the start of a comment, but found whitespace instead.");
                } else {
                    throw new SyntaxError("Was expecting the start of a comment, but found '" + (char) codePoint + "' instead.");
                }
            }
        }

        //We're past the initiating character(s)
        if (multiLine) {
            if (codePoint != '\n' && Character.isWhitespace(codePoint)) {
                if (startOfLine) return true;
            } else if (codePoint == '\n') {
                startOfLine = true;
            } else {
                if (startOfLine) startOfLine = false;
            }

            if (codePoint == '/' && prevChar == '*') {
                result.deleteCharAt(result.length() - 1); //Get rid of the *
                done = true;
                return true;
            } else {
                prevChar = codePoint;
                result.append((char) codePoint);
                return true;
            }
        } else {
            if (codePoint == '\n') {
                done = true;
                return true;
            } else {
                prevChar = codePoint; //Not really necessary but whatever. For consistency! :)
                result.append((char) codePoint);
                return true;
            }
        }
    }

    @Override
    public void eof() throws SyntaxError {
        if (multiLine) throw new SyntaxError("Unexpected end-of-file while reading a multiline comment.");
    }

    @Override
    public boolean isComplete() {
        return done;
    }

    @Override
    public String getResult() throws SyntaxError {
        return result.toString().trim();
    }

}
