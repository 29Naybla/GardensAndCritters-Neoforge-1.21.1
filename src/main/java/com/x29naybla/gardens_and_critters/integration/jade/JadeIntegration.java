package com.x29naybla.gardens_and_critters.integration.jade;

import com.x29naybla.gardens_and_critters.GardensAndCritters;
import com.x29naybla.gardens_and_critters.common.entity.Snail;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class JadeIntegration implements IWailaPlugin {
    public static final ResourceLocation SNAIL_SPEED = ResourceLocation.fromNamespaceAndPath(GardensAndCritters.MODID, "snail_speed");

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerEntityComponent(SnailSpeedProvider.SNAIL_SPEED, Snail.class);
    }
}
