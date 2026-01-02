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
