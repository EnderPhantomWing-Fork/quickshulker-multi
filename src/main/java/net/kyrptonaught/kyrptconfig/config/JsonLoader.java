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


import java.io.InputStream;

public interface JsonLoader {

    AbstractConfigFile loadFromString(String input, Class<? extends AbstractConfigFile> output) throws Exception;

    AbstractConfigFile loadFromInputStream(InputStream input, Class<? extends AbstractConfigFile> output) throws Exception;

    String toString(AbstractConfigFile config) throws Exception;
}
