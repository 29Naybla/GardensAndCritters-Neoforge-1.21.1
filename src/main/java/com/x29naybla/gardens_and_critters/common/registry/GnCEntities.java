package com.x29naybla.gardens_and_critters.common.registry;

import com.x29naybla.gardens_and_critters.GardensAndCritters;
import com.x29naybla.gardens_and_critters.common.entity.Snail;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class GnCEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, GardensAndCritters.MODID);

    public static final Supplier<EntityType<Snail>> SNAIL =
            ENTITY_TYPES.register("snail", () -> EntityType.Builder.of(Snail::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.75f).build("snail"));

    public static void register(IEventBus bus) {
        ENTITY_TYPES.register(bus);
    }
}
