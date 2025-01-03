package com.keid.divinearsenal;

import blue.endless.jankson.annotation.Nullable;
import com.keid.divinearsenal.events.EventRegister;
import com.keid.divinearsenal.items.ItemInit;
import com.mrcrayfish.framework.api.FrameworkAPI;
import com.mrcrayfish.framework.api.event.EntityEvents;
import com.mrcrayfish.framework.api.sync.Serializers;
import com.mrcrayfish.framework.api.sync.SyncedClassKey;
import com.mrcrayfish.framework.api.sync.SyncedDataKey;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.resource.Resource;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.EntityHitResult;
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

		FieldRegistrationHandler.register(ItemInit.class, MOD_ID, false);


	}


}