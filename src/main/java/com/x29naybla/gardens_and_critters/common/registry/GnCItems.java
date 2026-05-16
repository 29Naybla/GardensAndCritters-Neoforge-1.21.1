package com.x29naybla.gardens_and_critters.common.registry;

import com.x29naybla.gardens_and_critters.GardensandCritters;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GnCItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GardensandCritters.MODID);

    public static final DeferredItem<Item> SNAIL_SPAWN_EGG = ITEMS.register("snail_spawn_egg",
            () -> new DeferredSpawnEggItem(GnCEntities.SNAIL, 0xe4d9a6, 0x6e4628,
                    new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
