package com.keid.divinearsenal.items.nameless.trinkets;

import blue.endless.jankson.annotation.Nullable;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;


public class NamelessCompass extends TrinketItem {
    public NamelessCompass() {
        super(new OwoItemSettings().stacksTo(1).rarity(Rarity.UNCOMMON));
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {

        int timer = stack.getOrCreateTag().getInt("timer");
        int rank = stack.getOrCreateTag().getInt("rank");

        if (rank < 1){
            rank = 1;
        }

        int ranktime = 36000 / rank;

        if (timer < ranktime){
            stack.getOrCreateTag().putInt("timer", timer + 1);
            stack.getOrCreateTag().putBoolean("active", false);
        } else {
            stack.getOrCreateTag().putBoolean("active", true);
        }

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        int timer = stack.getOrCreateTag().getInt("timer");
        int rank = stack.getOrCreateTag().getInt("rank");
        boolean active = stack.getOrCreateTag().getBoolean("active");

        if (rank < 1){
            rank = 1;
        }

        tooltip.add(Component.literal("Rank " + String.valueOf(rank)).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("When you are about to die, teleport back to your spawn point"));
        if (active){
            tooltip.add(Component.literal("active").withStyle(ChatFormatting.GREEN));
        } else {
            int ranktime = 36000 / rank;
            float sec = (float) ranktime / 20;
            float timerSec = (float) timer / 20;

            float secLeft = sec - timerSec;

            DecimalFormat df = new DecimalFormat("##.#");
            df.setRoundingMode(RoundingMode.DOWN);

            tooltip.add(Component.literal("inactive for another ").append(Component.literal(String.valueOf(df.format(secLeft))).withStyle(ChatFormatting.RED)).append(Component.literal(" seconds")));
        }
        tooltip.add(Component.literal("(only works if you have a spawnpoint set)").withStyle(ChatFormatting.GRAY));
    }
}
