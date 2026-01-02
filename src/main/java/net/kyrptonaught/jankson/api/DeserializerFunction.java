package net.kyrptonaught.jankson.api;

import net.kyrptonaught.jankson.impl.serializer.InternalDeserializerFunction;

@FunctionalInterface
public interface DeserializerFunction<A, B> extends InternalDeserializerFunction<B> {
    B apply(A a, Marshaller m) throws DeserializationException;

    @SuppressWarnings("unchecked")
    @Override
    default B deserialize(Object a, Marshaller m) throws DeserializationException {
        try {
            return apply((A) a, m);
        } catch (ClassCastException ex) {
            throw new DeserializationException(ex);
        }
    }
}
