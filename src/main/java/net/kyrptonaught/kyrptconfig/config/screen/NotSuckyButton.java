/*
 * This file is part of the Quick Shulker Multi project, licensed under the MIT License.
 *
 * Copyright (C) 2019 kyrptonaught, Hao_cen, Grayer0113, MoRanpcy, EnderPhantomWing and other contributors
 *
 * {name} is free software: you can redistribute or modify it under the terms of the MIT License.
 *
 * Browse the MIT License here. <https://mit-license.org/>
 */

package net.kyrptonaught.kyrptconfig.config.screen;

//#if MC >= 1.21.2
//$$ import net.minecraft.util.ARGB;
//$$ import net.minecraft.client.renderer.RenderType;
//#else
import com.mojang.blaze3d.systems.RenderSystem;
//#endif
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class NotSuckyButton extends Button {
    int buttonColor = 16777215;
    public boolean disableHover = false;
    private static final WidgetSprites TEXTURES = new WidgetSprites(ResourceLocation.parse("widget/button"), ResourceLocation.parse("widget/button_disabled"), ResourceLocation.parse("widget/button_highlighted"));

    public NotSuckyButton(int x, int y, int width, int height, Component message, OnPress onPress) {
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION);
    }

    public void setButtonColor(int color) {
        this.buttonColor = color;
    }

    public boolean detectHover(int mouseX, int mouseY) {
        return mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height;
    }

    @Override
    public void renderWidget(GuiGraphics context, int mouseX, int mouseY, float delta) {
        //This can fix text rendering over the wrong btn
        //context.getMatrices().translate(0, 0,  1);

        if (disableHover) isHovered = false;

        //#if MC >= 1.21.2
        //$$ context.blitSprite(
        //$$         RenderType::guiTextured,
        //$$         TEXTURES.get(this.active, this.isHoveredOrFocused()),
        //$$         this.getX(),
        //$$         this.getY(),
        //$$         this.getWidth(),
        //$$         this.getHeight(),
        //$$         ARGB.white(this.alpha));
        //
        //$$ Font textRenderer = Minecraft.getInstance().font;
        //$$ int i = this.active ? buttonColor : 0xA0A0A0;
        //$$ renderString(context, textRenderer, i);
        //#else
        context.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        context.blitSprite(TEXTURES.get(this.active, this.isHoveredOrFocused()), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        context.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        Font textRenderer = Minecraft.getInstance().font;
        int i = this.active ? buttonColor : 0xA0A0A0;
        renderString(context, textRenderer, i);
        //#endif
    }
}
