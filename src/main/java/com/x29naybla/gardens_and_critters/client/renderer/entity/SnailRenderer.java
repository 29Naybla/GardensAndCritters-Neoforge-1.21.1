package com.x29naybla.gardens_and_critters.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.x29naybla.gardens_and_critters.GardensandCritters;
import com.x29naybla.gardens_and_critters.client.model.SnailModel;
import com.x29naybla.gardens_and_critters.common.entity.Snail;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SnailRenderer extends MobRenderer<Snail, SnailModel<Snail>> {
    public SnailRenderer(EntityRendererProvider.Context context) {
        super(context, new SnailModel<>(context.bakeLayer(SnailModel.LAYER_LOCATION)), 0.45F);
    }

    @Override
    public ResourceLocation getTextureLocation(Snail entity) {
        return ResourceLocation.fromNamespaceAndPath(GardensandCritters.MODID, "textures/entity/snail.png");
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
