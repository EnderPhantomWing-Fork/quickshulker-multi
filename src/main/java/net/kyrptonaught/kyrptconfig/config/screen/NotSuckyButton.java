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
//$$ import net.minecraft.util.math.ColorHelper;
//$$ import net.minecraft.client.render.RenderLayer;
//#else
import com.mojang.blaze3d.systems.RenderSystem;
//#endif
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ButtonTextures;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.util.Identifier;
//#if MC >= 1.21.11
//$$ import net.minecraft.text.Style;
//$$ import net.minecraft.text.Texts;
//#else
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;
//#endif
//#if MC >= 1.21.8
//$$ import net.minecraft.client.gl.RenderPipelines;
//#else
//#endif

public class NotSuckyButton extends ButtonWidget {
    //#if MC >= 1.21.8
    //$$ int buttonColor = -1;
    //#else
    int buttonColor = 16777215;
    //#endif
    public boolean disableHover = false;
    private static final ButtonTextures TEXTURES = new ButtonTextures(Identifier.of("widget/button"), Identifier.of("widget/button_disabled"), Identifier.of("widget/button_highlighted"));

    //#if MC >= 1.21.11
    //$$ public NotSuckyButton(int x, int y, int width, int height, net.minecraft.text.Text message, PressAction onPress) {
    //#else
    public NotSuckyButton(int x, int y, int width, int height, Text message, PressAction onPress) {
    //#endif
        super(x, y, width, height, message, onPress, DEFAULT_NARRATION_SUPPLIER);
    }

    public void setButtonColor(int color) {
        //#if MC >= 1.21.11
        //$$ this.setMessage(Texts.withStyle(this.getMessage(), Style.EMPTY.withColor(color)));
        //#else
        //#endif
        this.buttonColor = color;
    }

    public boolean detectHover(int mouseX, int mouseY) {
        return mouseX >= this.getX() && mouseY >= this.getY() && mouseX < this.getX() + this.width && mouseY < this.getY() + this.height;
    }

    @Override
    //#if MC >= 1.21.11
    //$$ protected void drawIcon(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
    //#else
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
    //#endif
        //This can fix text rendering over the wrong btn
        //context.getMatrices().translate(0, 0,  1);

        if (disableHover) hovered = false;
        //#if MC >= 1.21.11
        //$$ this.drawButton(context);
        //$$ this.drawLabel(context.getTextConsumer());
        //#elseif MC >= 1.21.8
        //$$ context.drawGuiTexture(
        //$$         RenderPipelines.GUI_TEXTURED,
        //$$         TEXTURES.get(this.active, this.isSelected()),
        //$$         this.getX(),
        //$$         this.getY(),
        //$$         this.getWidth(),
        //$$         this.getHeight(),
        //$$         ColorHelper.getWhite(this.alpha));
        //$$ TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        //$$ int i = ColorHelper.withAlpha(this.alpha, this.active ? buttonColor : -6250336);
        //$$ drawMessage(context, textRenderer, i);
        //#elseif MC >= 1.21.2
        //$$ context.drawGuiTexture(
        //$$         RenderLayer::getGuiTextured,
        //$$         TEXTURES.get(this.active, this.isSelected()),
        //$$         this.getX(),
        //$$         this.getY(),
        //$$         this.getWidth(),
        //$$         this.getHeight(),
        //$$         ColorHelper.getWhite(this.alpha));
        //$$ TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        //$$ int i = this.active ? buttonColor : 0xA0A0A0;
        //$$ drawMessage(context, textRenderer, i);
        //#else
        context.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        context.drawGuiTexture(TEXTURES.get(this.active, this.isSelected()), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        int i = this.active ? buttonColor : 0xA0A0A0;
        drawMessage(context, textRenderer, i);
        //#endif
    }
}
