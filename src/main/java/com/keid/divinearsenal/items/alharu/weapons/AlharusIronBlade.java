package com.keid.divinearsenal.items.alharu.weapons;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static com.keid.divinearsenal.data.DataInit.SPARK_COUNTER;
import static net.minecraft.item.ToolMaterials.IRON;

public class AlharusIronBlade extends SwordItem {
    public AlharusIronBlade() {
        super(IRON, 3, -2.4f, new Settings().rarity(Rarity.UNCOMMON));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker){

        if(target instanceof Monster && !target.isAlive()){

            PlayerEntity player = (PlayerEntity) attacker;
            System.out.println("trigger!");

            int newCount = SPARK_COUNTER.getValue(player) + 1;
            SPARK_COUNTER.setValue(player, newCount);

            player.sendMessage(Text.literal("You have: " + SPARK_COUNTER.getValue(player) + " Spark counters"), true);
        }

        return super.postHit(stack, target, attacker);
    }

}
