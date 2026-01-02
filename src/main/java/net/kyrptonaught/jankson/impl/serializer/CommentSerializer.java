/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.jankson.impl.serializer;

import net.kyrptonaught.jankson.JsonGrammar;

import java.io.IOException;
import java.io.Writer;

public class CommentSerializer {

    public static void print(Writer writer, String comment, int indent, JsonGrammar grammar) throws IOException {
        if (comment == null || comment.trim().isEmpty()) return;
        StringBuilder b = new StringBuilder(comment.length());
        print(b, comment, indent, grammar);
        writer.append(b);
    }

    public static void print(StringBuilder builder, String comment, int indent, JsonGrammar grammar) {
        boolean comments = grammar.hasComments();
        boolean whitespace = grammar.shouldOutputWhitespace();
        print(builder, comment, indent, comments, whitespace);
    }

    // Note: Indent may be -1.
    public static void print(StringBuilder builder, String comment, int indent, boolean comments, boolean whitespace) {
        if (!comments) return;
        if (comment == null || comment.trim().isEmpty()) return;

        if (whitespace) {
            if (comment.contains("\n")) {
                //Use /* */ comment
                builder.append("/* ");
                String[] lines = comment.split("\\n");
                for (int i = 0; i < lines.length; i++) {
                    String line = lines[i];
                    if (i != 0) builder.append("   ");
                    builder.append(line);
                    builder.append('\n');
                    for (int j = 0; j < indent + 1; j++) {
                        builder.append('\t');
                    }
                }
                builder.append("*/\n");
                for (int i = 0; i < indent + 1; i++) {
                    builder.append('\t');
                }
            } else {
                //Use a single-line comment
                builder.append("// ");
                builder.append(comment);
                builder.append('\n');
                for (int i = 0; i < indent + 1; i++) {
                    builder.append('\t');
                }
            }
        } else {
            //Always use /* */ comments

            if (comment.contains("\n")) {
                //Split the lines into separate /* */ comments and string them together inline.

                String[] lines = comment.split("\\n");
                for (int i = 0; i < lines.length; i++) {
                    String line = lines[i];
                    builder.append("/* ");
                    builder.append(line);
                    builder.append(" */ ");
                }
            } else {
                builder.append("/* ");
                builder.append(comment);
                builder.append(" */ ");
            }
        }
    }
}
