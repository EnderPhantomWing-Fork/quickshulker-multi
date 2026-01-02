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

public interface ParserContext<T> {
    /**
     * Consume one codepoint from the stream, and either use it to continue composing the result or to discover that
     * the result is complete and processing should stop. Throws a SyntaxError if unexpected or nonsense characters are
     * encountered.
     */

    boolean consume(int codePoint, Jankson loader) throws SyntaxError;

    /**
     * Notifies this context that the file ended abruptly while in this context and before isComplete returned true. In
     * some contexts, like a single-line comment, this is fine. In most contexts, this should throw a descriptive error.
     */
    void eof() throws SyntaxError;

    /**
     * Returns true if the parser has assembled a complete result. After true is returned, no more code points will be
     * offered to consume, and getResult will soon be called to retrieve the result.
     */
    boolean isComplete();

    /**
     * Gets the result of parsing. Will be called only after isComplete reports true and processing of input has
     * ceased.
     */
    T getResult() throws SyntaxError;
}
