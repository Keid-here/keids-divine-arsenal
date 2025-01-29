package com.keid.divinearsenal.events.entity;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Chicken;

import static com.keid.divinearsenal.data.DataInit.HIT_COUNT;

public class ChickenHitCounter {
    public static void register(){
        AttackEntityCallback.EVENT.register((player, world, hand, entity, direction) -> {

            if(entity instanceof Chicken animal)
            {
                int newCount = HIT_COUNT.getValue(animal) + 1;
                HIT_COUNT.setValue(animal, newCount);

                player.displayClientMessage(Component.literal("This animal has been hit " + newCount + " times!"), true);
            }
            return InteractionResult.PASS;
        });
    }
}
