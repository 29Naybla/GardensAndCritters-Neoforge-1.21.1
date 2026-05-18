package com.x29naybla.gardens_and_critters.client.renderer.entity;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.gardens_and_critters.GardensandCritters;
import com.x29naybla.gardens_and_critters.client.model.SnailModel;
import com.x29naybla.gardens_and_critters.common.entity.Snail;
import com.x29naybla.gardens_and_critters.common.entity.SnailVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class SnailRenderer extends MobRenderer<Snail, SnailModel<Snail>> {
    private static final Map<SnailVariant, ResourceLocation> LOCATION_BY_VARIANT_RIGHT =
            Util.make(Maps.newEnumMap(SnailVariant.class), map -> {
                map.put(SnailVariant.CREAM,
                        ResourceLocation.fromNamespaceAndPath(GardensandCritters.MODID, "textures/entity/snail/right/snail_cream.png"));
                map.put(SnailVariant.GREEN,
                        ResourceLocation.fromNamespaceAndPath(GardensandCritters.MODID, "textures/entity/snail/right/snail_green.png"));
                map.put(SnailVariant.NAUTILUS,
                        ResourceLocation.fromNamespaceAndPath(GardensandCritters.MODID, "textures/entity/snail/right/snail_nautilus.png"));
            });

    private static final Map<SnailVariant, ResourceLocation> LOCATION_BY_VARIANT_LEFT =
            Util.make(Maps.newEnumMap(SnailVariant.class), map -> {
                map.put(SnailVariant.CREAM,
                        ResourceLocation.fromNamespaceAndPath(GardensandCritters.MODID, "textures/entity/snail/left/snail_cream.png"));
                map.put(SnailVariant.GREEN,
                        ResourceLocation.fromNamespaceAndPath(GardensandCritters.MODID, "textures/entity/snail/left/snail_green.png"));
                map.put(SnailVariant.NAUTILUS,
                        ResourceLocation.fromNamespaceAndPath(GardensandCritters.MODID, "textures/entity/snail/left/snail_nautilus.png"));
            });

    public SnailRenderer(EntityRendererProvider.Context context) {
        super(context, new SnailModel<>(context.bakeLayer(SnailModel.LAYER_LOCATION)), 0.45F);
    }

    @Override
    public ResourceLocation getTextureLocation(Snail entity) {
        if (entity.isLeftHanded()){
            return LOCATION_BY_VARIANT_LEFT.get(entity.getVariant());
        } else
            return LOCATION_BY_VARIANT_RIGHT.get(entity.getVariant());
    }

    @Override
    public void render(Snail entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
