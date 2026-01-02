package net.kyrptonaught.jankson;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Tagging class for Json objects, arrays, and primitives
 */
public abstract class JsonElement implements Cloneable {
    public abstract JsonElement clone();

    public String toJson() {
        return toJson(false, false, 0);
    }

    public String toJson(boolean comments, boolean newlines) {
        return toJson(comments, newlines, 0);
    }

    @Deprecated
    public abstract String toJson(boolean comments, boolean newlines, int depth);

    public String toJson(JsonGrammar grammar, int depth) {
        StringWriter w = new StringWriter();
        try {
            toJson(w, grammar, depth);
            w.flush();
            return w.toString();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String toJson(JsonGrammar grammar) {
        return toJson(grammar, 0);
    }

    public abstract void toJson(Writer writer, JsonGrammar grammar, int depth) throws IOException;
}
