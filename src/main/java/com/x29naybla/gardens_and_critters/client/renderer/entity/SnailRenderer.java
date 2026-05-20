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
                map.put(SnailVariant.CREAM, getRightTexture("cream"));
                map.put(SnailVariant.GREEN, getRightTexture("green"));
                map.put(SnailVariant.BLACK, getRightTexture("black"));
                map.put(SnailVariant.LIME, getRightTexture("lime"));
                map.put(SnailVariant.LEMON, getRightTexture("lemon"));
                map.put(SnailVariant.NAUTILUS, getRightTexture("nautilus"));
            });

    private static final Map<SnailVariant, ResourceLocation> LOCATION_BY_VARIANT_LEFT =
            Util.make(Maps.newEnumMap(SnailVariant.class), map -> {
                map.put(SnailVariant.CREAM, getLeftTexture("cream"));
                map.put(SnailVariant.GREEN, getLeftTexture("green"));
                map.put(SnailVariant.BLACK, getLeftTexture("black"));
                map.put(SnailVariant.LIME, getLeftTexture("lime"));
                map.put(SnailVariant.LEMON, getLeftTexture("lemon"));
                map.put(SnailVariant.NAUTILUS, getLeftTexture("nautilus"));
            });

    private static ResourceLocation getRightTexture(String name){
        return ResourceLocation.fromNamespaceAndPath(GardensandCritters.MODID, "textures/entity/snail/right/snail_"+name+".png");
    }

    private static ResourceLocation getLeftTexture(String name){
        return ResourceLocation.fromNamespaceAndPath(GardensandCritters.MODID, "textures/entity/snail/left/snail_"+name+".png");
    }

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
