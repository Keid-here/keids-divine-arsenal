package com.keid.divinearsenal;

import com.keid.divinearsenal.gui.hud.HudReg;
import io.wispforest.owo.ui.hud.Hud;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

import static com.keid.divinearsenal.KeidsDivineArsenal.MOD_ID;
import static com.keid.divinearsenal.data.DataInit.SPARK_COUNTER;

public class KeidsDivineArsenalClient implements ClientModInitializer {

	//Keybindings
	KeyBinding binding1 = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.keids-divine-arsenal.GUI", // The translation key of the keybinding's name
			InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
			GLFW.GLFW_KEY_K, // The keycode of the key
			"category.keids-divine-arsenal.gui_cat" // The translation key of the keybinding's category.
	));

	public static Identifier HUD = new Identifier(MOD_ID, "hud");
	public static boolean hudToggle = true;



	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.

		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			while (binding1.wasPressed()) {

				if (!hudToggle){
					hudToggle = true;
					client.player.sendMessage(Text.literal("HUD toggled on"), false);
				}else {
					hudToggle = false;
					client.player.sendMessage(Text.literal("HUD toggled off"), false);
					Hud.remove(HUD);
				}
			}
		});

		ClientTickEvents.END_CLIENT_TICK.register((client) -> {
			if (hudToggle){
				HudReg.register();
			}
		});


	}
}