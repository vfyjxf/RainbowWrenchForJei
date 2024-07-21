package dev.vfyjxf.rwfj;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.ITickTimer;
import mezz.jei.gui.TickTimer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class InternalDrawable implements IDrawable {

    private final int width;
    private final int height;
    private final ITickTimer tickTimer;
    private final ResourceLocation resourceLocation = new ResourceLocation(Tags.MOD_ID, "textures/config_button_cheat.png");

    public InternalDrawable(int width, int height) {
        this.width = width;
        this.height = height;
        this.tickTimer = new TickTimer(25, 360, false);
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void draw(@Nonnull Minecraft minecraft, int xOffset, int yOffset) {
        Minecraft mc = Minecraft.getMinecraft();

        float scale = 0.95f;
        int angle = tickTimer.getValue() % 360;
        float red = (float) Math.abs(Math.sin(Math.toRadians(angle)));
        float green = (float) Math.abs(Math.sin(Math.toRadians(angle + 120)));
        float blue = (float) Math.abs(Math.sin(Math.toRadians(angle + 240)));

        mc.getTextureManager().bindTexture(resourceLocation);
        GlStateManager.pushMatrix();
        GlStateManager.translate(xOffset + width / 2f, yOffset + height / 2f, 0);
        GlStateManager.color(red, green, blue, 1.0f);
        GlStateManager.rotate(angle, 0.0f, 0.0f, 1.0f);
        GlStateManager.scale(scale, scale, 1.0f);
        GlStateManager.translate(-(width / 2f) / scale, -(height / 2f) / scale, 0);
        Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, width, height, width, height);
        GlStateManager.popMatrix();
    }
}