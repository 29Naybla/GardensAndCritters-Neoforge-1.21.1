package com.x29naybla.gardens_and_critters.data;

import com.x29naybla.gardens_and_critters.GardensAndCritters;
import com.x29naybla.gardens_and_critters.common.registry.GnCBlocks;
import com.x29naybla.gardens_and_critters.common.tag.GnCTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTags extends ItemTagsProvider {
    public ItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                    CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, GardensAndCritters.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        //Gardens and Critters Tags
        tag(GnCTags.Items.PLANTER_SUBSTRATES)
                .addTag(net.minecraft.tags.ItemTags.DIRT)
                .addTag(net.minecraft.tags.ItemTags.SMELTS_TO_GLASS)
                .add(Items.CRIMSON_NYLIUM)
                .add(Items.WARPED_NYLIUM)
                .add(Items.SOUL_SAND)
                .add(Items.END_STONE)
                .addOptional(ResourceLocation.parse("farmersdelight:organic_compost"));

        tag(GnCTags.Items.SUSTAINS_MUSHROOMS)
                .add(Items.MYCELIUM)
                .add(Items.PODZOL)
                .add(Items.CRIMSON_NYLIUM)
                .add(Items.WARPED_NYLIUM)
                .addOptional(ResourceLocation.parse("farmersdelight:organic_compost"))
                .addOptional(ResourceLocation.parse("farmersdelight:rich_soil"));

        tag(GnCTags.Items.SNAIL_FOOD)
                .add(Items.CARROT)
                .add(Items.GOLDEN_CARROT)
                .add(Items.DANDELION)
                .addTag(Tags.Items.EGGS);

        //Common Tags
        tag(Tags.Items.DYED_WHITE)
                .add(GnCBlocks.WHITE_PLANTER.asItem());
        tag(Tags.Items.DYED_LIGHT_GRAY)
                .add(GnCBlocks.LIGHT_GRAY_PLANTER.asItem());
        tag(Tags.Items.DYED_GRAY)
                .add(GnCBlocks.GRAY_PLANTER.asItem());
        tag(Tags.Items.DYED_BLACK)
                .add(GnCBlocks.BLACK_PLANTER.asItem());
        tag(Tags.Items.DYED_BROWN)
                .add(GnCBlocks.BROWN_PLANTER.asItem());
        tag(Tags.Items.DYED_RED)
                .add(GnCBlocks.RED_PLANTER.asItem());
        tag(Tags.Items.DYED_ORANGE)
                .add(GnCBlocks.ORANGE_PLANTER.asItem());
        tag(Tags.Items.DYED_YELLOW)
                .add(GnCBlocks.YELLOW_PLANTER.asItem());
        tag(Tags.Items.DYED_LIME)
                .add(GnCBlocks.LIME_PLANTER.asItem());
        tag(Tags.Items.DYED_GREEN)
                .add(GnCBlocks.GREEN_PLANTER.asItem());
        tag(Tags.Items.DYED_CYAN)
                .add(GnCBlocks.CYAN_PLANTER.asItem());
        tag(Tags.Items.DYED_LIGHT_BLUE)
                .add(GnCBlocks.LIGHT_BLUE_PLANTER.asItem());
        tag(Tags.Items.DYED_BLUE)
                .add(GnCBlocks.BLUE_PLANTER.asItem());
        tag(Tags.Items.DYED_PURPLE)
                .add(GnCBlocks.PURPLE_PLANTER.asItem());
        tag(Tags.Items.DYED_MAGENTA)
                .add(GnCBlocks.MAGENTA_PLANTER.asItem());
        tag(Tags.Items.DYED_PINK)
                .add(GnCBlocks.PINK_PLANTER.asItem());

        //Vanilla Tags
        tag(net.minecraft.tags.ItemTags.DIRT)
                .addOptional(ResourceLocation.parse("farmersdelight:rich_soil"));
    }
}
