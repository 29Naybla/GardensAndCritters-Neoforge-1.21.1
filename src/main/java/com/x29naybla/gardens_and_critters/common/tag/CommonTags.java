package com.x29naybla.gardens_and_critters.common.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CommonTags {
    public static class Blocks {
        public static final TagKey<Block> MUSHROOMS = commonBlockTag("mushrooms");

        private static TagKey<Block> commonBlockTag(String path) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
        }
    }

    public static class Items {

        private static TagKey<Item> commonItemTag(String path){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("c", path));
        }
    }
}
