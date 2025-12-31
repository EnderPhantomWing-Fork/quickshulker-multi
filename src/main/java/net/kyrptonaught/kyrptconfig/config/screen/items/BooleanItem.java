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

import net.kyrptonaught.kyrptconfig.config.screen.NotSuckyButton;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
//#if MC >= 1.21.10
//$$ import net.minecraft.client.gui.Click;
//#else
//#endif

public class BooleanItem extends ConfigItem<Boolean> {
    private final NotSuckyButton boolWidget;

    public BooleanItem(Text name, Boolean value, Boolean defaultValue) {
        super(name, value, defaultValue);
        this.boolWidget = new NotSuckyButton(0, 0, 100, 20, Text.literal("BoolButton"), widget -> {
            setValue(!this.value);
        });
        setValue(value);
        useDefaultResetBTN();
    }

    @Override
    public void setValue(Boolean value) {
        super.setValue(value);
        if (value) {
            boolWidget.setMessage(Text.translatable("key.kyrptconfig.config.true"));
            boolWidget.setButtonColor(DyeColor.LIME.getFireworkColor());
        } else {
            boolWidget.setMessage(Text.translatable("key.kyrptconfig.config.false"));
            boolWidget.setButtonColor(DyeColor.RED.getSignColor());
        }
    }

    //#if MC >= 1.21.10
    //$$  @Override
    //$$  public void mouseClicked(Click click, boolean doubled) {
    //$$      super.mouseClicked(click, doubled);
    //$$      boolWidget.mouseClicked(click, doubled);
    //$$  }
    //#else
    @Override
    public void mouseClicked(double mouseX, double mouseY, int button) {
        super.mouseClicked(mouseX, mouseY, button);
        boolWidget.mouseClicked(mouseX, mouseY, button);
    }
    //#endif

    @Override
    public void render(DrawContext context, int x, int y, int mouseX, int mouseY, float delta) {
        super.render(context, x, y, mouseX, mouseY, delta);
        this.boolWidget.setY(y);
        this.boolWidget.setX(resetButton.getX() - resetButton.getWidth() - (boolWidget.getWidth() / 2) - 20);

        boolWidget.render(context, mouseX, mouseY, delta);
    }
}