package com.keid.divinearsenal.screen;

import com.keid.divinearsenal.KeidsDivineArsenal;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;

import static com.keid.divinearsenal.KeidsDivineArsenal.MOD_ID;

public class ModScreenHandlers {
    public static final MenuType<DivineAnvilScreenHandler> DIVINEANVIL_SCREEN_HANDLER =
            Registry.register(BuiltInRegistries.MENU, new ResourceLocation(MOD_ID, "divinecrafting"),
                    new ExtendedScreenHandlerType<>(DivineAnvilScreenHandler::new));

    public static void registerScreenHandlers() {
        KeidsDivineArsenal.LOGGER.info("Registering Screen Handlers for " + MOD_ID);
    }
}
