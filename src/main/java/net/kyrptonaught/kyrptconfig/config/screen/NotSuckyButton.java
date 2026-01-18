package net.kyrptonaught.kyrptconfig.config.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button.OnPress;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;

public class NotSuckyButton extends Button {
    int buttonColor = 16777215;
    public boolean disableHover = false;
    private static final WidgetSprites TEXTURES = new WidgetSprites(ResourceLocation.tryParse("widget/button"), ResourceLocation.tryParse("widget/button_disabled"), ResourceLocation.tryParse("widget/button_highlighted"));

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

        context.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();
        context.blitSprite(TEXTURES.get(this.active, this.isHoveredOrFocused()), this.getX(), this.getY(), this.getWidth(), this.getHeight());
        context.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        Font textRenderer = Minecraft.getInstance().font;
        int i = this.active ? buttonColor : 0xA0A0A0;
        renderString(context, textRenderer, i);
    }
}
