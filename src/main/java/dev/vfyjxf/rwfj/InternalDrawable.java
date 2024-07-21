package dev.vfyjxf.rwfj;

import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.ITickTimer;
import mezz.jei.gui.TickTimer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.util.vector.Quaternion;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InternalDrawable implements IDrawable {

    private final int width;
    private final int height;
    private final ITickTimer tickTimer;
    private final List<ResourceLocation> resourceLocations = Stream.of(
            "textures/config_button_cheat0.png",
            "textures/config_button_cheat1.png",
            "textures/config_button_cheat2.png",
            "textures/config_button_cheat3.png",
            "textures/config_button_cheat4.png",
            "textures/config_button_cheat5.png",
            "textures/config_button_cheat6.png",
            "textures/config_button_cheat7.png"
    ).map(it -> new ResourceLocation(Tags.MOD_ID, it)).collect(Collectors.toList());

    public InternalDrawable(int width, int height) {
        this.width = width;
        this.height = height;
        this.tickTimer = new TickTimer(20, 7, false);
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
        int index = tickTimer.getValue();
        mc.getTextureManager().bindTexture(resourceLocations.get(index));
        GlStateManager.pushMatrix();
        Gui.drawModalRectWithCustomSizedTexture(xOffset, yOffset, 0, 0, width, height, width, height);
        GlStateManager.popMatrix();
    }


}
