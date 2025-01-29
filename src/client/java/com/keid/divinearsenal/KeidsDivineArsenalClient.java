package com.keid.divinearsenal;

import com.keid.divinearsenal.gui.hud.HudRenderReg;
import com.keid.divinearsenal.gui.screen.DivineAnvilScreen;
import com.keid.divinearsenal.screen.ModScreenHandlers;
import com.mojang.blaze3d.platform.InputConstants;
import io.wispforest.owo.ui.hud.Hud;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

import static com.keid.divinearsenal.KeidsDivineArsenal.MOD_ID;


public class KeidsDivineArsenalClient implements ClientModInitializer {

	//Keybindings
	KeyMapping binding1 = KeyBindingHelper.registerKeyBinding(new KeyMapping(
			"key.keids-divine-arsenal.GUI", // The translation key of the keybinding's name
			InputConstants.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
			GLFW.GLFW_KEY_K, // The keycode of the key
			"category.keids-divine-arsenal.gui_cat" // The translation key of the keybinding's category.
	));

	public static ResourceLocation HUD = new ResourceLocation(MOD_ID, "hud");
	public static boolean hudToggle = true;



	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			while (binding1.isDown()) {

				if (!hudToggle){
					hudToggle = true;
					client.player.sendSystemMessage(Component.nullToEmpty("HUD toggled on"));
				}else {
					hudToggle = false;
					client.player.sendSystemMessage(Component.nullToEmpty("HUD toggled on"));
					Hud.remove(HUD);
				}
			}
		});

		HudRenderReg.register();

		MenuScreens.register(ModScreenHandlers.DIVINEANVIL_SCREEN_HANDLER, DivineAnvilScreen::new);

		//keys
		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			if (hudToggle){
				// HudReg.register();
			}
		});


	}
}