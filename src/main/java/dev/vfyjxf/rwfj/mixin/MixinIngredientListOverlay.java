package dev.vfyjxf.rwfj.mixin;

import dev.vfyjxf.rwfj.InternalDrawable;
import mezz.jei.Internal;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.gui.GuiHelper;
import mezz.jei.gui.overlay.ConfigButton;
import mezz.jei.gui.overlay.IngredientListOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Mixin(value = IngredientListOverlay.class, remap = false)
public abstract class MixinIngredientListOverlay {

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lmezz/jei/gui/overlay/ConfigButton;create(Lmezz/jei/gui/overlay/IngredientListOverlay;)Lmezz/jei/gui/overlay/ConfigButton;"), remap = false)
    private ConfigButton redirectConstructor(IngredientListOverlay parent) {
        try {
            Constructor<ConfigButton> constructor = ConfigButton.class.getDeclaredConstructor(IDrawable.class, IDrawable.class, IngredientListOverlay.class);
            constructor.setAccessible(true);
            GuiHelper guiHelper = Internal.getHelpers().getGuiHelper();
            return constructor.newInstance(guiHelper.getConfigButtonIcon(), new InternalDrawable(16, 16), parent);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
