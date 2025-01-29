package com.keid.divinearsenal.blocks;

import com.keid.divinearsenal.KeidsDivineArsenal;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

import static com.keid.divinearsenal.KeidsDivineArsenal.MOD_ID;

public class ModBlockEntities {
    public static final BlockEntityType<DivineanvilBlockEntity> DIVINEANVIL_BLOCK_ENTITY =
            Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(MOD_ID, "divineanvil"),
                    FabricBlockEntityTypeBuilder.create(DivineanvilBlockEntity::new,
                            BlockInit.DIVINEANVIL).build());

    public static void registerBlockEntities() {
        KeidsDivineArsenal.LOGGER.info("Registering Block Entities for " + MOD_ID);
    }
}
