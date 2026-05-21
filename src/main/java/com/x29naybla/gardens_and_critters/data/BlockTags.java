package com.x29naybla.gardens_and_critters.data;

import com.x29naybla.gardens_and_critters.GardensAndCritters;
import com.x29naybla.gardens_and_critters.common.registry.GnCBlocks;
import com.x29naybla.gardens_and_critters.common.tag.GnCTags;
import com.x29naybla.gardens_and_critters.common.tag.CommonTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTags extends BlockTagsProvider {
    public BlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, GardensAndCritters.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        //Gardens and Critters Tags
        tag(GnCTags.Blocks.PLANTERS)
                .add(GnCBlocks.PLANTER.get())
                .add(GnCBlocks.WHITE_PLANTER.get())
                .add(GnCBlocks.LIGHT_GRAY_PLANTER.get())
                .add(GnCBlocks.GRAY_PLANTER.get())
                .add(GnCBlocks.BLACK_PLANTER.get())
                .add(GnCBlocks.BROWN_PLANTER.get())
                .add(GnCBlocks.RED_PLANTER.get())
                .add(GnCBlocks.ORANGE_PLANTER.get())
                .add(GnCBlocks.YELLOW_PLANTER.get())
                .add(GnCBlocks.LIME_PLANTER.get())
                .add(GnCBlocks.GREEN_PLANTER.get())
                .add(GnCBlocks.CYAN_PLANTER.get())
                .add(GnCBlocks.LIGHT_BLUE_PLANTER.get())
                .add(GnCBlocks.BLUE_PLANTER.get())
                .add(GnCBlocks.PURPLE_PLANTER.get())
                .add(GnCBlocks.MAGENTA_PLANTER.get())
                .add(GnCBlocks.PINK_PLANTER.get());

        tag(GnCTags.Blocks.SUSTAINS_MUSHROOMS)
                .addTag(net.minecraft.tags.BlockTags.MUSHROOM_GROW_BLOCK);

        tag(GnCTags.Blocks.DIRT_SUSTAINS)
                .addTag(net.minecraft.tags.BlockTags.SAPLINGS)
                .addTag(CommonTags.Blocks.MUSHROOMS)
                .add(Blocks.CRIMSON_FUNGUS)
                .add(Blocks.WARPED_FUNGUS)
                .add(Blocks.SHORT_GRASS)
                .add(Blocks.FERN)
                .add(Blocks.DEAD_BUSH)
                .addTag(net.minecraft.tags.BlockTags.SMALL_FLOWERS)
                .add(Blocks.BAMBOO_SAPLING)
                .add(Blocks.BAMBOO)
                .add(Blocks.CRIMSON_ROOTS)
                .add(Blocks.WARPED_ROOTS)
                .add(Blocks.NETHER_SPROUTS)
                .add(Blocks.TALL_GRASS)
                .add(Blocks.LARGE_FERN)
                .addTag(net.minecraft.tags.BlockTags.TALL_FLOWERS)
                .add(Blocks.BIG_DRIPLEAF)
                .add(Blocks.SMALL_DRIPLEAF)
                .addTag(net.minecraft.tags.BlockTags.CROPS)
                .add(Blocks.SWEET_BERRY_BUSH);

        tag(GnCTags.Blocks.MYCELIUM_SUSTAINS)
                .addTag(GnCTags.Blocks.DIRT_SUSTAINS);

        tag(GnCTags.Blocks.SAND_SUSTAINS)
                .add(Blocks.DEAD_BUSH)
                .add(Blocks.BAMBOO_SAPLING)
                .add(Blocks.BAMBOO)
                .add(Blocks.CACTUS);

        tag(GnCTags.Blocks.SOUL_SAND_SUSTAINS)
                .add(Blocks.WITHER_ROSE)
                .add(Blocks.NETHER_WART);

        tag(GnCTags.Blocks.NYLIUM_SUSTAINS)
                .addTag(CommonTags.Blocks.MUSHROOMS)
                .add(Blocks.CRIMSON_FUNGUS)
                .add(Blocks.WARPED_FUNGUS)
                .add(Blocks.CRIMSON_ROOTS)
                .add(Blocks.WARPED_ROOTS)
                .add(Blocks.NETHER_SPROUTS);

        tag(GnCTags.Blocks.END_STONE_SUSTAINS)
                .add(Blocks.CHORUS_FLOWER);

        tag(GnCTags.Blocks.SNAILS_SPAWNABLE_ON)
                .add(Blocks.GRASS_BLOCK)
                .add(Blocks.COARSE_DIRT)
                .add(Blocks.PODZOL);

        //Common Tags
        tag(Tags.Blocks.DYED_WHITE)
                .add(GnCBlocks.WHITE_PLANTER.get());
        tag(Tags.Blocks.DYED_LIGHT_GRAY)
                .add(GnCBlocks.LIGHT_GRAY_PLANTER.get());
        tag(Tags.Blocks.DYED_GRAY)
                .add(GnCBlocks.GRAY_PLANTER.get());
        tag(Tags.Blocks.DYED_BLACK)
                .add(GnCBlocks.BLACK_PLANTER.get());
        tag(Tags.Blocks.DYED_BROWN)
                .add(GnCBlocks.BROWN_PLANTER.get());
        tag(Tags.Blocks.DYED_RED)
                .add(GnCBlocks.RED_PLANTER.get());
        tag(Tags.Blocks.DYED_ORANGE)
                .add(GnCBlocks.ORANGE_PLANTER.get());
        tag(Tags.Blocks.DYED_YELLOW)
                .add(GnCBlocks.YELLOW_PLANTER.get());
        tag(Tags.Blocks.DYED_LIME)
                .add(GnCBlocks.LIME_PLANTER.get());
        tag(Tags.Blocks.DYED_GREEN)
                .add(GnCBlocks.GREEN_PLANTER.get());
        tag(Tags.Blocks.DYED_CYAN)
                .add(GnCBlocks.CYAN_PLANTER.get());
        tag(Tags.Blocks.DYED_LIGHT_BLUE)
                .add(GnCBlocks.LIGHT_BLUE_PLANTER.get());
        tag(Tags.Blocks.DYED_BLUE)
                .add(GnCBlocks.BLUE_PLANTER.get());
        tag(Tags.Blocks.DYED_PURPLE)
                .add(GnCBlocks.PURPLE_PLANTER.get());
        tag(Tags.Blocks.DYED_MAGENTA)
                .add(GnCBlocks.MAGENTA_PLANTER.get());
        tag(Tags.Blocks.DYED_PINK)
                .add(GnCBlocks.PINK_PLANTER.get());

        tag(CommonTags.Blocks.MUSHROOMS)
                .add(Blocks.BROWN_MUSHROOM)
                .add(Blocks.RED_MUSHROOM)
                .addOptional(ResourceLocation.parse("farmersdelight:brown_mushroom_colony"))
                .addOptional(ResourceLocation.parse("farmersdelight:red_mushroom_colony"));

        
    }
}
