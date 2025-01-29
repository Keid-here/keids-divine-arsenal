package com.keid.divinearsenal.events.trinkets;

import dev.emi.trinkets.api.TrinketsApi;
import io.wispforest.owo.ops.WorldOps;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import java.util.Optional;

import static com.keid.divinearsenal.items.ItemInit.NAMELESSCOMPASS;


public class NamelessCompassHelper {
    public static void register(){
        ServerLivingEntityEvents.ALLOW_DEATH.register((entity, source, amount) -> {

            if (!entity.level().isClientSide && entity instanceof ServerPlayer && TrinketsApi.getTrinketComponent(entity).get().isEquipped(NAMELESSCOMPASS)) {

                ItemStack itemStack = TrinketsApi.getTrinketComponent(entity).get().getEquipped(NAMELESSCOMPASS).get(0).getB();
                boolean active = itemStack.getOrCreateTag().getBoolean("active");

                ServerPlayer player = (ServerPlayer) entity;

                ServerLevel spawnWorld = player.getServer().getLevel(player.getRespawnDimension());


                    if (active && player.getRespawnPosition() != null) {
                        Optional<Vec3> posOptional = Player.findRespawnPositionAndUseSpawnBlock(spawnWorld, player.getRespawnPosition(), player.getRespawnAngle(), true, false);
                        if (posOptional.isPresent()) {
                            WorldOps.teleportToWorld(player, spawnWorld, posOptional.get());
                        } else {
                            Vec3 posWorldSpawn = spawnWorld.getSharedSpawnPos().getCenter();
                            WorldOps.teleportToWorld(player, spawnWorld, posWorldSpawn);
                        }
                        itemStack.getOrCreateTag().putInt("timer", 0);
                        itemStack.getOrCreateTag().putBoolean("active", false);
                        player.setHealth(2);
                        return false;
                    }
            }
            return true;
        });

    }
}
