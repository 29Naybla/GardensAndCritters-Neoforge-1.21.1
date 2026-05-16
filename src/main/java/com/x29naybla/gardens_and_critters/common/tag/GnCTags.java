package com.x29naybla.gardens_and_critters.common.tag;

import com.x29naybla.gardens_and_critters.GardensandCritters;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class GnCTags {
    public static class Blocks {
        public static final TagKey<Block> PLANTERS = createTag("planters");
        public static final TagKey<Block> SUSTAINS_MUSHROOMS = createTag("sustains_mushrooms");

        public static final TagKey<Block> DIRT_SUSTAINS = createTag("dirt_sustains");
        public static final TagKey<Block> MYCELIUM_SUSTAINS = createTag("mycelium_sustains");
        public static final TagKey<Block> SAND_SUSTAINS = createTag("sand_sustains");
        public static final TagKey<Block> SOUL_SAND_SUSTAINS = createTag("soul_sand_sustains");
        public static final TagKey<Block> NYLIUM_SUSTAINS = createTag("nylium_sustains");
        public static final TagKey<Block> END_STONE_SUSTAINS = createTag("end_stone_sustains");


        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(GardensandCritters.MODID, name));
        }

        private static TagKey<Block> externalBlockTag(String modId, String path) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(modId, path));
        }
    }

    public static class Items {
        public static final TagKey<Item> PLANTER_SUBSTRATES = createTag("planter_substrates");
        public static final TagKey<Item> SUSTAINS_MUSHROOMS = createTag("sustains_mushrooms");

        public static final TagKey<Item> SNAIL_FOOD = createTag("snail_food");
        public static final TagKey<Item> SNAIL_TEMPT_ITEMS =  createTag("snail_tempt_items");

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(GardensandCritters.MODID, name));
        }

        private static TagKey<Item> externalItemTag(String modId, String path) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(modId, path));
        }
    }

    public static class Entities {


        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(GardensandCritters.MODID, name));
        }
    }

}
