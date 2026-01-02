/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.kyrptconfig.config;

import net.kyrptonaught.jankson.Jankson;

import java.io.InputStream;

public class JanksonJsonLoader implements JsonLoader {
    private Jankson jankson;

    public void provideJankson(Jankson jankson) {
        this.jankson = jankson;
    }

    public Jankson getJankson() {
        return jankson;
    }

    @Override
    public AbstractConfigFile loadFromString(String input, Class<? extends AbstractConfigFile> output) throws Exception {
        return jankson.fromJson(input, output);
    }

    @Override
    public AbstractConfigFile loadFromInputStream(InputStream input, Class<? extends AbstractConfigFile> output) throws Exception {
        return jankson.fromJson(jankson.load(input), output);
    }

    @Override
    public String toString(AbstractConfigFile config) {
        return jankson.toJson(config).toJson(true, true);
    }
}
