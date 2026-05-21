package com.x29naybla.gardens_and_critters.common.registry;

import com.x29naybla.gardens_and_critters.GardensAndCritters;
import com.x29naybla.gardens_and_critters.common.block.PlanterBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class GnCBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(GardensAndCritters.MODID);

    public static final DeferredBlock<Block> PLANTER = registerBlock("planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TERRACOTTA)));
    public static final DeferredBlock<Block> WHITE_PLANTER = registerBlock("white_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.WHITE_TERRACOTTA)));
    public static final DeferredBlock<Block> LIGHT_GRAY_PLANTER = registerBlock("light_gray_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_GRAY_TERRACOTTA)));
    public static final DeferredBlock<Block> GRAY_PLANTER = registerBlock("gray_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GRAY_TERRACOTTA)));
    public static final DeferredBlock<Block> BLACK_PLANTER = registerBlock("black_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_TERRACOTTA)));
    public static final DeferredBlock<Block> BROWN_PLANTER = registerBlock("brown_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BROWN_TERRACOTTA)));
    public static final DeferredBlock<Block> RED_PLANTER = registerBlock("red_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.RED_TERRACOTTA)));
    public static final DeferredBlock<Block> ORANGE_PLANTER = registerBlock("orange_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ORANGE_TERRACOTTA)));
    public static final DeferredBlock<Block> YELLOW_PLANTER = registerBlock("yellow_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.YELLOW_TERRACOTTA)));
    public static final DeferredBlock<Block> LIME_PLANTER = registerBlock("lime_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LIME_TERRACOTTA)));
    public static final DeferredBlock<Block> GREEN_PLANTER = registerBlock("green_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.GREEN_TERRACOTTA)));
    public static final DeferredBlock<Block> CYAN_PLANTER = registerBlock("cyan_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CYAN_TERRACOTTA)));
    public static final DeferredBlock<Block> LIGHT_BLUE_PLANTER = registerBlock("light_blue_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.LIGHT_BLUE_TERRACOTTA)));
    public static final DeferredBlock<Block> BLUE_PLANTER = registerBlock("blue_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_TERRACOTTA)));
    public static final DeferredBlock<Block> PURPLE_PLANTER = registerBlock("purple_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PURPLE_TERRACOTTA)));
    public static final DeferredBlock<Block> MAGENTA_PLANTER = registerBlock("magenta_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MAGENTA_TERRACOTTA)));
    public static final DeferredBlock<Block> PINK_PLANTER = registerBlock("pink_planter",
            () -> new PlanterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_TERRACOTTA)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block){
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block){
        GnCItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
