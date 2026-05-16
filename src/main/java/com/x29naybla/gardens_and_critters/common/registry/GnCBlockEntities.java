package com.x29naybla.gardens_and_critters.common.registry;

import com.x29naybla.gardens_and_critters.GardensandCritters;
import com.x29naybla.gardens_and_critters.common.block.PlanterBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class GnCBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, GardensandCritters.MODID);

    public static final Supplier<BlockEntityType<PlanterBlockEntity>> PLANTER_BE =
            BLOCK_ENTITIES.register("planter_be", () -> BlockEntityType.Builder.of(
                    PlanterBlockEntity::new,
                    GnCBlocks.PLANTER.get(),
                    GnCBlocks.WHITE_PLANTER.get(),
                    GnCBlocks.LIGHT_GRAY_PLANTER.get(),
                    GnCBlocks.GRAY_PLANTER.get(),
                    GnCBlocks.BLACK_PLANTER.get(),
                    GnCBlocks.BROWN_PLANTER.get(),
                    GnCBlocks.RED_PLANTER.get(),
                    GnCBlocks.ORANGE_PLANTER.get(),
                    GnCBlocks.YELLOW_PLANTER.get(),
                    GnCBlocks.LIME_PLANTER.get(),
                    GnCBlocks.GREEN_PLANTER.get(),
                    GnCBlocks.CYAN_PLANTER.get(),
                    GnCBlocks.LIGHT_BLUE_PLANTER.get(),
                    GnCBlocks.BLUE_PLANTER.get(),
                    GnCBlocks.PURPLE_PLANTER.get(),
                    GnCBlocks.MAGENTA_PLANTER.get(),
                    GnCBlocks.PINK_PLANTER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
