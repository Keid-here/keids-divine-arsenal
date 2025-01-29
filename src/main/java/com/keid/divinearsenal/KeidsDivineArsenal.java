package com.keid.divinearsenal;

import com.keid.divinearsenal.blocks.BlockInit;
import com.keid.divinearsenal.blocks.ModBlockEntities;
import com.keid.divinearsenal.events.EventRegister;
import com.keid.divinearsenal.items.ItemInit;
import com.keid.divinearsenal.screen.ModScreenHandlers;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeidsDivineArsenal implements ModInitializer {
	public static final String MOD_ID = "keids-divine-arsenal";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);



	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("what a beautiful day to load!");


		EventRegister.go();

		BlockInit.registerModBlocks();
		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();


		FieldRegistrationHandler.register(ItemInit.class, MOD_ID, false);




	}


}