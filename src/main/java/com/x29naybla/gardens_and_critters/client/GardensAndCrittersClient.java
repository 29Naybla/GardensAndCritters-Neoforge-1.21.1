package com.x29naybla.gardens_and_critters.client;

import com.x29naybla.gardens_and_critters.GardensAndCritters;
import com.x29naybla.gardens_and_critters.client.renderer.block.PlanterBlockEntityRenderer;
import com.x29naybla.gardens_and_critters.client.renderer.entity.SnailRenderer;
import com.x29naybla.gardens_and_critters.common.registry.GnCBlockEntities;
import com.x29naybla.gardens_and_critters.common.registry.GnCEntities;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(value = GardensAndCritters.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = GardensAndCritters.MODID, value = Dist.CLIENT)
public class GardensAndCrittersClient {
    public GardensAndCrittersClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    static void onClientSetup(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(GnCBlockEntities.PLANTER_BE.get(), PlanterBlockEntityRenderer::new);

        EntityRenderers.register(GnCEntities.SNAIL.get(), SnailRenderer::new);
    }
}
