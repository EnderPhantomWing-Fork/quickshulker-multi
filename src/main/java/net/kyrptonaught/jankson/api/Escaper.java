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

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public final class Escaper {
    private static final Set<Character.UnicodeBlock> DEFAULT_BLOCKS;

    static {
        HashSet<Character.UnicodeBlock> tmp = new HashSet<>();
        tmp.add(Character.UnicodeBlock.BASIC_LATIN);
        DEFAULT_BLOCKS = Collections.unmodifiableSet(tmp);
    }

    private Escaper() {
    }

    public static String escapeString(String s) {
        return escapeString(s, '"', DEFAULT_BLOCKS);
    }

    /**
     * Escapes a string such that the result is valid as the contents of a java, js, or JSON string,
     * and the JavaScript unescape() function will restore the original string. Additionally, this
     * method attempts to do the minimum amount of escaping required to accomplish these goals.
     *
     * @param s         The String to escape special characters in
     * @param quoteChar the kind of quote used to delimit the String, either 0x22 (") or 0x27 ('). If you don't need quotes escaped, use 0x00.
     * @return A copy of the String, but with special characters escaped
     */
    public static String escapeString(String s, char quoteChar, Set<Character.UnicodeBlock> unquotedBlocks) {
        StringBuilder result = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            //Encode easy stuff
            switch (ch) {
                case '\\':
                    result.append("\\\\");
                    break;
                case '\r':
                    result.append("\\r");
                    break;
                case '\n':
                    result.append("\\n");
                    break;
                case '\b':
                    result.append("\\b");
                    break;
                case '\f':
                    result.append("\\f");
                    break;
                case '\t':
                    result.append("\\t");
                    break;
                case '\"':
                    if (quoteChar == ch) {
                        result.append("\\\"");
                    } else {
                        result.append(ch);
                    }
                    break;
                case '\'':
                    if (quoteChar == ch) {
                        result.append("\\'");
                    } else {
                        result.append(ch);
                    }
                    break;
                default:
                    if (Character.isBmpCodePoint(ch)) {
                        //Use Unicode notation if it's not especially printable - lies in a special Unicode block, is a control character, etc.
                        Character.UnicodeBlock block = Character.UnicodeBlock.of(ch);

                        if (ch != 65535 && !Character.isISOControl(ch) && block != null && unquotedBlocks.contains(block)) { //Note: 65535 is the value of awt's KeyEvent.CHARACTER_UNDEFINED. Just in case it leaks into a document.
                            result.append(ch);
                        } else {
                            result.append(unicodeEscape(ch));
                        }
                    } else {
                        //Always use Unicode notation
                        i++;
                        char upper = s.charAt(i);
                        int codePoint = Character.toCodePoint(ch, upper);
                        result.append(unicodeEscape(codePoint));
                    }
                    break;
            }
        }
        return result.toString();
    }

    private static String unicodeEscape(int codePoint) {
        String codeString = Integer.toHexString(codePoint);
        while (codeString.length() < 4) codeString = "0" + codeString;
        return "\\u" + codeString;
    }

    //public static String unescapeString(String s) {
    //	return s;
    //}
}
