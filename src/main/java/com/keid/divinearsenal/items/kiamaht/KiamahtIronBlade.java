package com.keid.divinearsenal.items.kiamaht;

import static com.keid.divinearsenal.data.DataInit.HATE_COUNTER;
import static com.keid.divinearsenal.data.DataInit.SPARK_COUNTER;
import static net.minecraft.world.item.Tiers.IRON;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;

public class KiamahtIronBlade extends SwordItem {
    public KiamahtIronBlade() {
        super(IRON, 3, -2.4f, new Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){

        if(target instanceof Enemy && !target.isAlive()){

            Player player = (Player) attacker;
            System.out.println("trigger!");

            int newCount = HATE_COUNTER.getValue(player) + 1;
            HATE_COUNTER.setValue(player, newCount);

            player.displayClientMessage(Component.literal("You have: " + HATE_COUNTER.getValue(player) + " Hate counters"), true);
        }

        return super.hurtEnemy(stack, target, attacker);
    }

}
