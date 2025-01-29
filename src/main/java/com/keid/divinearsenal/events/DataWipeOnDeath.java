package com.keid.divinearsenal.events;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.world.entity.player.Player;

import static com.keid.divinearsenal.data.DataInit.HATE_COUNTER;
import static com.keid.divinearsenal.data.DataInit.SPARK_COUNTER;

public class DataWipeOnDeath {
    public static void register(){
        ServerPlayerEvents.AFTER_RESPAWN.register((serverPlayerEntity, serverPlayerEntity1, b) -> {
            Player player = serverPlayerEntity1;


            SPARK_COUNTER.setValue(player, 0);
            HATE_COUNTER.setValue(player, 0);
        });
    }
}
