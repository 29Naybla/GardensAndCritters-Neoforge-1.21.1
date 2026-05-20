package com.x29naybla.gardens_and_critters.common.event;

import com.x29naybla.gardens_and_critters.GardensandCritters;
import com.x29naybla.gardens_and_critters.client.model.SnailModel;
import com.x29naybla.gardens_and_critters.common.entity.Snail;
import com.x29naybla.gardens_and_critters.common.registry.GnCEntities;
import com.x29naybla.gardens_and_critters.common.registry.GnCItems;
import com.x29naybla.gardens_and_critters.common.tag.GnCTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.LevelAccessor;
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
                GnCEvents::checkSnailSpawnRules, RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    public static boolean checkSnailSpawnRules(
            EntityType<? extends Animal> animal, LevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random
    ) {
        boolean flag = MobSpawnType.ignoresLightRequirements(spawnType) || level.getRawBrightness(pos, 0) > 8;
        return level.getBlockState(pos.below()).is(GnCTags.Blocks.SNAILS_SPAWNABLE_ON) && flag;
    }
}
