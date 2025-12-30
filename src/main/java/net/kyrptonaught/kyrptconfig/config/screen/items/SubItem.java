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

package net.kyrptonaught.kyrptconfig.config.screen.items;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class SubItem<E> extends ConfigItem<E> {
    protected boolean expanded = false;
    protected int subStart = 0;
    protected List<ConfigItem<?>> configs = new ArrayList<>();

    public SubItem(Text name, boolean isExpanded) {
        super(name, null, null);
        this.expanded = isExpanded;
    }

    public SubItem(Text name) {
        this(name, false);
    }

    public boolean requiresRestart() {
        for (ConfigItem<?> item : configs) {
            if (item.requiresRestart())
                return true;
        }
        return super.requiresRestart();
    }

    public void save() {
        for (ConfigItem<?> item : configs)
            item.save();
        super.save();
    }

    public boolean isValueDefault() {
        for (ConfigItem<?> item : configs) {
            if (!item.isValueDefault())
                return false;
        }
        return true;
    }

    public void tick() {
        for (ConfigItem<?> item : configs) {
            if (item.isHidden()) continue;
            item.tick();
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        if (!isHidden() && mouseY > subStart && mouseY < subStart + 20)
            expanded = !expanded;

        if (expanded && !isHidden()) {
            for (ConfigItem<?> item : configs) {
                if (item.isHidden()) continue;
                item.mouseClicked(mouseX, mouseY, button);
            }
        }
    }

    public boolean charTyped(char chr, int modifiers) {
        if (expanded && !isHidden()) {
            for (ConfigItem<?> item : configs) {
                if (item.isHidden()) continue;
                if (item.charTyped(chr, modifiers))
                    return true;
            }
        }
        return false;
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (expanded && !isHidden()) {
            for (ConfigItem<?> item : configs) {
                if (item.isHidden()) continue;
                if (item.keyPressed(keyCode, scanCode, modifiers))
                    return true;
            }
        }
        return false;
    }

    public int getContentSize() {
        if (expanded && !isHidden()) {
            int size = 0;
            for (ConfigItem<?> item : configs) {
                if (item.isHidden()) continue;
                size += item.getSize() + 3;
            }
            return size;
        }
        return 0;
    }

    public void clearConfigItems() {
        configs.clear();
    }

    public ConfigItem<?> addConfigItem(ConfigItem<?> item) {
        this.configs.add(item);
        return item;
    }

    @Override
    public void render(DrawContext context, int x, int y, int mouseX, int mouseY, float delta) {
        super.render(context, x, y, mouseX, mouseY, delta);
        if (isHidden()) return;
        context.drawText(MinecraftClient.getInstance().textRenderer, expanded ? "-" : "+", x - 10, y + 5, 16777215, false);
        subStart = y;
        if (expanded) {
            int runningY = subStart + 23;
            for (ConfigItem<?> item : configs) {
                if (item.isHidden()) continue;
                item.render(context, 30, runningY, mouseX, mouseY, delta);
                runningY += item.getSize() + 3;
            }
        }
    }

    @Override
    public void render2(DrawContext context, int x, int y, int mouseX, int mouseY, float delta) {
        super.render2(context, x, y, mouseX, mouseY, delta);
        if (isHidden()) return;

        if (expanded) {
            int runningY = y + 23;
            for (ConfigItem<?> item : configs) {
                if (item.isHidden()) continue;
                item.render2(context, 30, runningY, mouseX, mouseY, delta);
                runningY += item.getSize() + 3;
            }
        }
    }
}
