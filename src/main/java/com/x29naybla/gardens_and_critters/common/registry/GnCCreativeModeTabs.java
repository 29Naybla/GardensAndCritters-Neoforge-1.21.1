package com.x29naybla.gardens_and_critters.common.registry;

import com.x29naybla.gardens_and_critters.GardensAndCritters;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class GnCCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GardensAndCritters.MODID);

    public static final Supplier<CreativeModeTab> GARDENS_AND_CRITTERS_BLOCKS_TAB = CREATIVE_MODE_TAB.register("gardens_and_critters_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(GnCBlocks.PLANTER.get()))
                    .title(Component.translatable("creativetab.gardens_and_critters.blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(GnCBlocks.PLANTER);
                        output.accept(GnCBlocks.WHITE_PLANTER);
                        output.accept(GnCBlocks.LIGHT_GRAY_PLANTER);
                        output.accept(GnCBlocks.GRAY_PLANTER);
                        output.accept(GnCBlocks.BLACK_PLANTER);
                        output.accept(GnCBlocks.BROWN_PLANTER);
                        output.accept(GnCBlocks.RED_PLANTER);
                        output.accept(GnCBlocks.ORANGE_PLANTER);
                        output.accept(GnCBlocks.YELLOW_PLANTER);
                        output.accept(GnCBlocks.LIME_PLANTER);
                        output.accept(GnCBlocks.GREEN_PLANTER);
                        output.accept(GnCBlocks.CYAN_PLANTER);
                        output.accept(GnCBlocks.LIGHT_BLUE_PLANTER);
                        output.accept(GnCBlocks.BLUE_PLANTER);
                        output.accept(GnCBlocks.PURPLE_PLANTER);
                        output.accept(GnCBlocks.MAGENTA_PLANTER);
                        output.accept(GnCBlocks.PINK_PLANTER);

                    }).build());

    //public static final Supplier<CreativeModeTab> GARDENS_AND_CRITTERS_ITEMS_TAB = CREATIVE_MODE_TAB.register("gardens_and_critters_items_tab",
    //        () -> CreativeModeTab.builder().icon(() -> new ItemStack(GnC.SUN.get()))
    //                .title(Component.translatable("creativetab.gardens_and_critters.items"))
    //                .displayItems((itemDisplayParameters, output) -> {
    //                }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
