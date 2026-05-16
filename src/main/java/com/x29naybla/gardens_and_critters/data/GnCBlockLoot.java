package com.x29naybla.gardens_and_critters.data;

import com.x29naybla.gardens_and_critters.common.registry.GnCBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class GnCBlockLoot extends BlockLootSubProvider {
    public GnCBlockLoot(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(GnCBlocks.PLANTER.get());
        dropSelf(GnCBlocks.WHITE_PLANTER.get());
        dropSelf(GnCBlocks.LIGHT_GRAY_PLANTER.get());
        dropSelf(GnCBlocks.GRAY_PLANTER.get());
        dropSelf(GnCBlocks.BLACK_PLANTER.get());
        dropSelf(GnCBlocks.BROWN_PLANTER.get());
        dropSelf(GnCBlocks.RED_PLANTER.get());
        dropSelf(GnCBlocks.ORANGE_PLANTER.get());
        dropSelf(GnCBlocks.YELLOW_PLANTER.get());
        dropSelf(GnCBlocks.LIME_PLANTER.get());
        dropSelf(GnCBlocks.GREEN_PLANTER.get());
        dropSelf(GnCBlocks.CYAN_PLANTER.get());
        dropSelf(GnCBlocks.LIGHT_BLUE_PLANTER.get());
        dropSelf(GnCBlocks.BLUE_PLANTER.get());
        dropSelf(GnCBlocks.PURPLE_PLANTER.get());
        dropSelf(GnCBlocks.MAGENTA_PLANTER.get());
        dropSelf(GnCBlocks.PINK_PLANTER.get());

    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return GnCBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
