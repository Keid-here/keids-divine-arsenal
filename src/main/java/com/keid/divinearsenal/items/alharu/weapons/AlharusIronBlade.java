package com.keid.divinearsenal.items.alharu.weapons;

import com.keid.divinearsenal.data.counter.SparkHelper;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;

import static com.keid.divinearsenal.data.DataInit.SPARK_COUNTER;
import static net.minecraft.world.item.Tiers.IRON;

public class AlharusIronBlade extends SwordItem {
    public AlharusIronBlade() {
        super(IRON, 3, -2.4f, new Properties().rarity(Rarity.UNCOMMON));
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker){

        if(target instanceof Monster && !target.isAlive()){

            Player player = (Player) attacker;
            System.out.println("trigger!");

            SparkHelper.add((ServerPlayer) player, 1);


        }

        return super.hurtEnemy(stack, target, attacker);
    }

}
