package com.keid.divinearsenal.blocks;


import com.keid.divinearsenal.KeidsDivineArsenal;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;

import static com.keid.divinearsenal.KeidsDivineArsenal.MOD_ID;

public class BlockInit {
    public static final Block DIVINEANVIL = registerBlock("divineanvil",
            new DivineAnvil(FabricBlockSettings.copyOf(Blocks.ANVIL).sound(SoundType.AMETHYST).noOcclusion()));



    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        KeidsDivineArsenal.LOGGER.info("Registering ModBlocks for " + MOD_ID);
    }
}
