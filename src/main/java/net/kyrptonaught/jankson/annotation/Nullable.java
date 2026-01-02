package net.kyrptonaught.jankson.annotation;

import javax.annotation.meta.TypeQualifierNickname;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * Alias for jsr305 javax.annotation.CheckForNull
 *
 * <p>Values annotated with this element should be treated the same way as Kotlin optional types: null is explicitly
 * indicated as a valid state, and should be checked before use.
 */
@Inherited
@TypeQualifierNickname
@Retention(RetentionPolicy.RUNTIME)
@javax.annotation.CheckForNull
public @interface Nullable {

}
