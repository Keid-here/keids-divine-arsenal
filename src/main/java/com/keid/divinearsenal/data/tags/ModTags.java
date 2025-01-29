package com.keid.divinearsenal.data.tags;

import static com.keid.divinearsenal.KeidsDivineArsenal.MOD_ID;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> createTag(String name) {
            return TagKey.create(Registries.BLOCK, new ResourceLocation(MOD_ID, name));
        }
    }

    public static class Items {
        // public static final TagKey<Item> UPGRADEABLE_ITEMS = createTag("upgradeable_items");

        /* private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(MOD_ID, name));
        }
         */

        public static final TagKey<Item> UPGRADEABLE_ITEMS = TagKey.create(Registries.ITEM, new ResourceLocation(MOD_ID, "upgradeable_items"));
    }
}
