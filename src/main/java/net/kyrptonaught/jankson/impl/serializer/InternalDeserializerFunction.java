package net.kyrptonaught.jankson.impl.serializer;

import net.kyrptonaught.jankson.api.DeserializationException;
import net.kyrptonaught.jankson.api.Marshaller;

@FunctionalInterface
public interface InternalDeserializerFunction<B> {
    B deserialize(Object a, Marshaller m) throws DeserializationException;
}
