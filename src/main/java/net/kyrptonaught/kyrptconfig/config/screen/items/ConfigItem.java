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
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Language;
import net.minecraft.util.math.ColorHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public abstract class ConfigItem<T> {
    private Text fieldTitle;
    private List<Text> toolTipText;
    protected Consumer<T> saveConsumer, valueUpdatedEvent;
    protected NotSuckyButton resetButton;
    protected T value, defaultValue;
    private boolean requiresRestart = false;
    private boolean isHidden = false;

    public ConfigItem(Text name, T value, T defaultValue) {
        this.fieldTitle = name;
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public ConfigItem<?> setSaveConsumer(Consumer<T> saveConsumer) {
        this.saveConsumer = saveConsumer;
        return this;
    }

    public ConfigItem<?> setValueUpdatedEvent(Consumer<T> valueUpdatedEvent) {
        this.valueUpdatedEvent = valueUpdatedEvent;
        return this;
    }

    public ConfigItem<?> setRequiresRestart() {
        requiresRestart = true;
        ((MutableText) fieldTitle).append(" *");
        return this;
    }

    public Text getTitleText(){
        return fieldTitle;
    }

    public ConfigItem<?> setTitleText(Text title) {
        this.fieldTitle = title;
        return this;
    }

    public ConfigItem<?> setToolTipWithNewLine(String translatableKey) {
        String[] translated = Language.getInstance().get(translatableKey).split("\n");
        this.toolTipText = new ArrayList<>();
        for (String line : translated) {
            this.toolTipText.add(Text.literal(line));
        }

        return this;
    }

    public ConfigItem<?> setToolTip(Text toolTip) {
        this.toolTipText = List.of(toolTip);
        return this;
    }

    public ConfigItem<?> setToolTip(Text... toolTips) {
        this.toolTipText = List.of(toolTips);
        return this;
    }

    public ConfigItem<?> setHidden(boolean hidden) {
        this.isHidden = hidden;
        return this;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public boolean requiresRestart() {
        return requiresRestart;
    }

    protected void runSaveConsumer(T value) {
        if (saveConsumer != null && value != null)
            saveConsumer.accept(value);
    }

    public void save() {
        runSaveConsumer(value);
    }

    public int getSize() {
        if (isHidden) return 0;
        return getHeaderSize() + getContentSize();
    }

    public int getHeaderSize() {
        return 20;
    }

    public int getContentSize() {
        return 0;
    }

    public void useDefaultResetBTN() {
        this.resetButton = new NotSuckyButton(0, 0, 35, 20, Text.translatable("key.kyrptconfig.config.reset"), widget -> {
            resetToDefault();
        });
    }

    public void resetToDefault() {
        setValue(defaultValue);
    }

    public boolean isValueDefault() {
        return value.equals(defaultValue);
    }

    public void setValue(T value) {
        this.value = value;
        if (valueUpdatedEvent != null)
            valueUpdatedEvent.accept(this.value);
    }

    public void tick() {
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        if (isHidden) return;
        if (resetButton != null)
            resetButton.mouseClicked(mouseX, mouseY, button);
    }

    public boolean charTyped(char chr, int modifiers) {
        return false;
    }

    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return false;
    }

    public void render(DrawContext context, int x, int y, int mouseX, int mouseY, float delta) {
        if (isHidden) return;

        int width = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int height = y + getHeaderSize();
        if (mouseY > y && mouseY < height)
            context.fill(0, y - 1, width, height + 1, ColorHelper.Argb.getArgb(50, 255, 255, 255));

        context.drawText(MinecraftClient.getInstance().textRenderer, this.fieldTitle, x, y + 6, 16777215, true);

        if (resetButton != null) {
            this.resetButton.setY(y);
            this.resetButton.setX(width - resetButton.getWidth() - 20);
            resetButton.active = !isValueDefault();
            resetButton.render(context, mouseX, mouseY, delta);
        }

    }

    public void render2(DrawContext context, int x, int y, int mouseX, int mouseY, float delta) {
        if (isHidden) return;
        if (mouseX > x && mouseX < x + MinecraftClient.getInstance().textRenderer.getWidth(fieldTitle) &&
                mouseY > y && mouseY < y + 12)
            renderToolTip(context, mouseX, mouseY);
    }

    public void renderToolTip(DrawContext context, int x, int y) {
        if (toolTipText != null && requiresRestart) {
            List<Text> newList = new ArrayList<>(toolTipText);
            newList.add(Text.translatable("key.kyrptconfig.config.restartRequired"));
            context.drawTooltip(MinecraftClient.getInstance().textRenderer, newList, x, y);
        } else if (toolTipText != null)
            context.drawTooltip(MinecraftClient.getInstance().textRenderer, toolTipText, x, y);
        else if (requiresRestart) {
            context.drawTooltip(MinecraftClient.getInstance().textRenderer, Text.translatable("key.kyrptconfig.config.restartRequired"), x, y);
        }
    }
}
