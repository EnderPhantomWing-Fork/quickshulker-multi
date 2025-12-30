/*
 * This file is part of the Quick Shulker Multi project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2025  Fallen_Breath and contributors
 *
 * Quick Shulker Multi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Quick Shulker Multi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Quick Shulker Multi.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.kyrptonaught.kyrptconfig.config;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class ConfigStorage {
    private final Path saveFile;
    public AbstractConfigFile config;
    private final AbstractConfigFile defaultConfig;
    private final JsonLoader jsonLoader;

    public ConfigStorage(Path fileName, AbstractConfigFile defaultConfig, JsonLoader jsonLoader) {
        this.saveFile = fileName;
        this.defaultConfig = defaultConfig;
        this.jsonLoader = jsonLoader;
    }

    public void save(String MOD_ID) {
        try (OutputStream os = Files.newOutputStream(saveFile); OutputStreamWriter out = new OutputStreamWriter(os, StandardCharsets.UTF_8)) {
            String json = jsonLoader.toString(config);
            out.write(json);
        } catch (Exception e) {
            System.out.println(getConfigName(MOD_ID, "Failed to save #CONFIG"));
            e.printStackTrace();
        }
    }

    public AbstractConfigFile load(String MOD_ID) {
        if (!Files.exists(saveFile) || !Files.isReadable(saveFile)) {
            System.out.println(getConfigName(MOD_ID, "Unable to find #CONFIG! Creating a default config"));
            config = defaultConfig;
            return config;
        }

        boolean failed = false;
        try (InputStream in = Files.newInputStream(saveFile, StandardOpenOption.READ)) {
            config = jsonLoader.loadFromInputStream(in, defaultConfig.getClass());
        } catch (Exception e) {
            failed = true;
            e.printStackTrace();
        }
        if (failed || (config == null)) {
            System.out.println(getConfigName(MOD_ID, "Failed to load #CONFIG! Overwriting with default config"));
            config = defaultConfig;
        }
        return config;
    }

    public AbstractConfigFile getDefaultConfig() {
        return defaultConfig;
    }

    private String getConfigName(String MOD_ID, String message) {
        return "[" + MOD_ID + "]: " + message.replaceAll("#CONFIG", "config: " + saveFile.getFileName().toString());
    }
}