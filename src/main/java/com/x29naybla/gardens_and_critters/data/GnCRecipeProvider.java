package com.x29naybla.gardens_and_critters.data;

import com.x29naybla.gardens_and_critters.common.registry.GnCBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class GnCRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public GnCRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput){
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GnCBlocks.PLANTER, 1)
                .pattern("# #")
                .pattern("# #")
                .pattern("###")
                .define('#', Items.BRICK)
                .unlockedBy("has_brick", has(Items.BRICK))
                .save(recipeOutput);

    }
}
