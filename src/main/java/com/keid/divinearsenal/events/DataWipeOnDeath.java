package com.keid.divinearsenal.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static com.keid.divinearsenal.data.DataInit.SPARK_COUNTER;

public class DataWipeOnDeath {
    public static void register(){
        ServerPlayerEvents.AFTER_RESPAWN.register((serverPlayerEntity, serverPlayerEntity1, b) -> {
            PlayerEntity player = serverPlayerEntity1;


            SPARK_COUNTER.setValue(player, 0);
        });
    }
}
