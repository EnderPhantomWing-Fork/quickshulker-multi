/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.quickshulker.api;

public interface ItemInventoryContainer {

    int QS$getUsedSlotInPlayerInv();

    default boolean hasItem() {
        return QS$getUsedSlotInPlayerInv() >= 0;
    }

    void QS$setUsedSlot(int playerInvSlotID);

}
