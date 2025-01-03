package com.keid.divinearsenal.events.entity;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import static com.keid.divinearsenal.data.DataInit.HIT_COUNT;

public class ChickenHitCounter {
    public static void register(){
        AttackEntityCallback.EVENT.register((player, world, hand, entity, direction) -> {

            if(entity instanceof ChickenEntity animal)
            {
                int newCount = HIT_COUNT.getValue(animal) + 1;
                HIT_COUNT.setValue(animal, newCount);

                player.sendMessage(Text.literal("This animal has been hit " + newCount + " times!"), true);
            }
            return ActionResult.PASS;
        });
    }
}
