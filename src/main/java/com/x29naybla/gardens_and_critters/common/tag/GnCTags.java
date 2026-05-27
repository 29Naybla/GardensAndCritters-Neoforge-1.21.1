package com.x29naybla.gardens_and_critters.common.tag;

import com.x29naybla.gardens_and_critters.GardensAndCritters;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
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

        public static final TagKey<Block> SNAILS_SPAWNABLE_ON = createTag("snails_spawnable_on");

        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(GardensAndCritters.MODID, name));
        }

        private static TagKey<Block> externalBlockTag(String modId, String path) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(modId, path));
        }
    }

    public static class Items {
        public static final TagKey<Item> PLANTER_SUBSTRATES = createTag("planter_substrates");
        public static final TagKey<Item> SUSTAINS_MUSHROOMS = createTag("sustains_mushrooms");

        public static final TagKey<Item> SNAIL_FOOD = createTag("snail_food");

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(GardensAndCritters.MODID, name));
        }

        private static TagKey<Item> externalItemTag(String modId, String path) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(modId, path));
        }
    }

    public static class Biomes {
        public static final TagKey<Biome> SNAILS_ARE_CREAM_VARIANT = createTag("snails_are_cream_variant");
        public static final TagKey<Biome> SNAILS_ARE_GREEN_VARIANT = createTag("snails_are_green_variant");
        public static final TagKey<Biome> SNAILS_ARE_BLACK_VARIANT = createTag("snails_are_black_variant");
        public static final TagKey<Biome> SNAILS_ARE_LIME_VARIANT = createTag("snails_are_lime_variant");
        public static final TagKey<Biome> SNAILS_ARE_LEMON_VARIANT = createTag("snails_are_lemon_variant");
        public static final TagKey<Biome> SNAILS_ARE_MUSHROOM_VARIANT = createTag("snails_are_mushroom_variant");

        public static final TagKey<Biome> SNAILS_ARE_PUMPKIN_VARIANT = createTag("snails_are_pumpkin_variant");
        public static final TagKey<Biome> SNAILS_ARE_NAUTILUS_VARIANT = createTag("snails_are_nautilus_variant");

        private static TagKey<Biome> createTag(String name) {
            return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(GardensAndCritters.MODID, name));
        }
    }

    public static class Entities {


        private static TagKey<EntityType<?>> createTag(String name) {
            return TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(GardensAndCritters.MODID, name));
        }
    }

}
