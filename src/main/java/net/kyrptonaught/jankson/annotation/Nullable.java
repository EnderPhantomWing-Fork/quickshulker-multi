/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

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
