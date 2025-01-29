package com.keid.divinearsenal.events.trinkets;

import com.keid.divinearsenal.data.counter.SparkHelper;
import com.keid.divinearsenal.items.alharu.trinkets.FightingSpirit;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import java.util.Objects;

import static com.keid.divinearsenal.items.ItemInit.FIGHTINGSPIRIT;

public class FightingSpiritHelper {
    public static void register(){
        AttackEntityCallback.EVENT.register((playerEntity, world, hand, entity1, entityHitResult) -> {

            if (!world.isClientSide && !playerEntity.hasEffect(MobEffects.DAMAGE_BOOST) && entity1 instanceof Monster && TrinketsApi.getTrinketComponent(playerEntity).get().isEquipped(FIGHTINGSPIRIT)) {
                if (SparkHelper.remove((ServerPlayer) playerEntity, 1, true)){
                    world.playSound(null, playerEntity.blockPosition(), SoundEvents.FIRECHARGE_USE, SoundSource.AMBIENT, 1F, 0.8F);

                    ItemStack itemStack = TrinketsApi.getTrinketComponent(playerEntity).get().getEquipped(FIGHTINGSPIRIT).get(0).getB();
                    int rank = itemStack.getOrCreateTag().getInt("rank");

                    int duration = 600 * rank;
                    int amplifier;
                    if (rank == 3 || rank == 4){
                        amplifier = 1;
                    } else if (rank >= 5){
                        amplifier = 2;
                    } else {
                        amplifier = 0;
                    }

                    playerEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, duration, amplifier));
                }
            }
            return InteractionResult.PASS;
        });
    }
}
