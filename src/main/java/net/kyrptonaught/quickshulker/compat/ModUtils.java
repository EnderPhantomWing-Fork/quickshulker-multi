/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.quickshulker.compat;

import net.fabricmc.loader.api.FabricLoader;

public class ModUtils {

    public static boolean isModLoad(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

}
