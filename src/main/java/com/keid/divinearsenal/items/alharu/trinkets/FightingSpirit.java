package com.keid.divinearsenal.items.alharu.trinkets;

import blue.endless.jankson.annotation.Nullable;
import dev.emi.trinkets.api.*;
import io.wispforest.owo.itemgroup.OwoItemSettings;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;

public class FightingSpirit extends TrinketItem {

    public FightingSpirit() {
        super(new OwoItemSettings().stacksTo(1));
    }

    /* for copy 7 paste purposes
    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        // +10% movement speed
        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, "keids-divine-arsenal:movement_speed", 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        // If the player has access to ring slots, this will give them an extra one
        SlotAttributes.addSlotModifier(modifiers, "hand/ring", uuid, 1, EntityAttributeModifier.Operation.ADDITION);

        return modifiers;
    }
     */

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        if (world.isClientSide) {
            return InteractionResultHolder.pass(user.getItemInHand(hand));
        }
        ItemStack heldStack = user.getItemInHand(hand);

        int rank = heldStack.getOrCreateTag().getInt("rank");
        heldStack.getOrCreateTag().putInt("rank", rank + 1);

        BlockEntity blockEntity = world.getBlockEntity(user.blockPosition());
        blockEntity.setChanged();

        return InteractionResultHolder.success(heldStack);



    }

    @Override
    public void onCraftedBy(ItemStack stack, Level world, Player player) {

    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag context) {
        int duration = 30;
        int rank = 1;
        if (stack.hasTag()) {
            duration = stack.getTag().getInt("rank") * 30;
            rank = stack.getTag().getInt("rank");
        }
        int amplifier;
        if (rank == 3 || rank == 4){
            amplifier = 2;
        } else if (rank >= 5){
            amplifier = 3;
        } else {
            amplifier = 1;
        }

        tooltip.add(Component.literal("Rank " + String.valueOf(rank)).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal("When attacking a hostile mob:"));
       tooltip.add(Component.literal("consume ").append(Component.literal("1 Spark").withStyle(ChatFormatting.RED)).append(Component.literal(", gain ")).append(Component.literal("Strength ").withStyle(ChatFormatting.AQUA)).append(Component.literal(String.valueOf(amplifier)).withStyle(ChatFormatting.AQUA)).append(Component.literal(" for ")).append(Component.literal(String.valueOf(duration))).append(Component.literal(" seconds")));
    }

}
