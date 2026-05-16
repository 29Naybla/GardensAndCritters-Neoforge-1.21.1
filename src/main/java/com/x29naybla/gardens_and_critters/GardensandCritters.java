package com.x29naybla.gardens_and_critters;

import com.x29naybla.gardens_and_critters.client.BuiltInPackSource;
import com.x29naybla.gardens_and_critters.common.registry.*;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.AddPackFindersEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforgespi.locating.IModFile;

@Mod(GardensandCritters.MODID)
public class GardensandCritters {
    public static final String MODID = "gardens_and_critters";

    public GardensandCritters(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);

        GnCItems.register(modEventBus);

        GnCBlocks.register(modEventBus);
        GnCBlockEntities.register(modEventBus);

        GnCEntities.register(modEventBus);

        GnCCreativeModeTabs.register(modEventBus);

        IModFile modFile = ModLoadingContext.get().getActiveContainer().getModInfo().getOwningFile().getFile();
        modEventBus.addListener((AddPackFindersEvent event) -> {
            if (event.getPackType() == PackType.CLIENT_RESOURCES) {
                event.addRepositorySource(new BuiltInPackSource(modFile, PackType.CLIENT_RESOURCES, "GardensAndCrittersPA", Component.translatable("pack.gardens_and_critters.gardens_and_critters_pa")));
            }
        });
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {}
}
