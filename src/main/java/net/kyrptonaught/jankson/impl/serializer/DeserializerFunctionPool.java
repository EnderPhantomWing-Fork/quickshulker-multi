package net.kyrptonaught.jankson.impl.serializer;

import net.kyrptonaught.jankson.*;
import net.kyrptonaught.jankson.api.DeserializationException;
import net.kyrptonaught.jankson.api.Marshaller;

import java.util.HashMap;

/**
 * Stores deserializer functions that produce objects of type &lt;B&gt;
 */
public class DeserializerFunctionPool<B> {
    private final Class<B> targetClass;
    private final HashMap<Class<?>, InternalDeserializerFunction<B>> values = new HashMap<>();

    public DeserializerFunctionPool(Class<B> targetClass) {
        this.targetClass = targetClass;
    }

    public void registerUnsafe(Class<?> sourceClass, InternalDeserializerFunction<B> function) {
        values.put(sourceClass, function);
    }

    public InternalDeserializerFunction<B> getFunction(Class<?> sourceClass) {
        return values.get(sourceClass);
    }

    public B apply(JsonElement elem, Marshaller marshaller) throws DeserializationException, FunctionMatchFailedException {
        InternalDeserializerFunction<B> selected = null;

        //This whole block is pretty ugly but there's a very particular selection order
        if (elem instanceof JsonPrimitive) {
            //1. Unwrapped primitive class
            Object obj = ((JsonPrimitive) elem).getValue();
            selected = values.get(obj.getClass());
            if (selected != null) return selected.deserialize(obj, marshaller);

            //2. JsonPrimitive
            selected = values.get(JsonPrimitive.class);
            if (selected != null) return selected.deserialize(elem, marshaller);
        } else if (elem instanceof JsonObject) {
            //2. JsonObject
            selected = values.get(JsonObject.class);
            if (selected != null) return selected.deserialize(elem, marshaller);
        } else if (elem instanceof JsonArray) {
            //2. JsonArray
            selected = values.get(JsonArray.class);
            if (selected != null) return selected.deserialize(elem, marshaller);
        }

        //3. JsonElement
        selected = values.get(JsonElement.class);
        if (selected != null) return selected.deserialize(elem, marshaller);

        //We can't just return null, *because null might be the intended output of one of the functions above!*
        throw new FunctionMatchFailedException("Couldn't find a deserializer in class '" + targetClass.getCanonicalName() + "' to unpack element '" + elem.toJson(JsonGrammar.JSON5) + "'.");
    }

    public static class FunctionMatchFailedException extends Exception {
        private static final long serialVersionUID = -7909332778483440658L;

        public FunctionMatchFailedException(String message) {
            super(message);
        }
    }
}
