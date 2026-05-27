package com.x29naybla.gardens_and_critters.client.renderer.entity;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.gardens_and_critters.GardensAndCritters;
import com.x29naybla.gardens_and_critters.client.model.SnailModel;
import com.x29naybla.gardens_and_critters.common.entity.Snail;
import com.x29naybla.gardens_and_critters.common.entity.SnailVariant;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;
import java.util.Map;

public class SnailRenderer extends MobRenderer<Snail, SnailModel<Snail>> {
    private static final Map<SnailVariant, ResourceLocation> LOCATION_BY_VARIANT_RIGHT =
            Util.make(Maps.newEnumMap(SnailVariant.class), map -> {
                for (SnailVariant snailVariant : SnailVariant.values()) {
                    map.put(snailVariant, ResourceLocation.fromNamespaceAndPath(GardensAndCritters.MODID, String.format(Locale.ROOT, "textures/entity/snail/right/snail_%s.png", snailVariant.getSerializedName())));
                }
            });

    private static final Map<SnailVariant, ResourceLocation> LOCATION_BY_VARIANT_LEFT =
            Util.make(Maps.newEnumMap(SnailVariant.class), map -> {
                for (SnailVariant snailVariant : SnailVariant.values()) {
                    map.put(snailVariant, ResourceLocation.fromNamespaceAndPath(GardensAndCritters.MODID, String.format(Locale.ROOT, "textures/entity/snail/left/snail_%s.png", snailVariant.getSerializedName())));
                }
            });

    public SnailRenderer(EntityRendererProvider.Context context) {
        super(context, new SnailModel<>(context.bakeLayer(SnailModel.LAYER_LOCATION)), 0.45F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(Snail entity) {
        if (entity.isLeftHanded()){
            return LOCATION_BY_VARIANT_LEFT.get(entity.getVariant());
        } else
            return LOCATION_BY_VARIANT_RIGHT.get(entity.getVariant());
    }

    @Override
    public void render(Snail entity, float entityYaw, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
