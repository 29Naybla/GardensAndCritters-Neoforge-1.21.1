package com.x29naybla.gardens_and_critters.common.event;

import com.x29naybla.gardens_and_critters.GardensandCritters;
import com.x29naybla.gardens_and_critters.client.model.SnailModel;
import com.x29naybla.gardens_and_critters.common.entity.Snail;
import com.x29naybla.gardens_and_critters.common.registry.GnCEntities;
import com.x29naybla.gardens_and_critters.common.registry.GnCItems;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = GardensandCritters.MODID)
public class GnCEvents {

    @SubscribeEvent
    public static void buildContents(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS){
            event.accept(GnCItems.SNAIL_SPAWN_EGG);
        }
    }

    //Entity registering
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(SnailModel.LAYER_LOCATION, SnailModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(GnCEntities.SNAIL.get(), Snail.createAttributes().build());
    }

    //Entity spawning
    @SubscribeEvent
    public static void registerSpawnPlacement(RegisterSpawnPlacementsEvent event){
        event.register(GnCEntities.SNAIL.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }
}
