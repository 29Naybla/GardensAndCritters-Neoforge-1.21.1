package com.x29naybla.gardens_and_critters.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.x29naybla.gardens_and_critters.GardensAndCritters;
import com.x29naybla.gardens_and_critters.client.animations.SnailAnimations;
import com.x29naybla.gardens_and_critters.common.entity.Snail;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public class SnailModel<T extends Snail> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(GardensAndCritters.MODID, "snail"), "main");
    private final ModelPart snail;
    private final ModelPart head;

    public SnailModel(ModelPart root) {
        this.snail = root.getChild("snail");
        this.head = this.snail.getChild("head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition snail = partdefinition.addOrReplaceChild("snail", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -1.0F));

        PartDefinition foot = snail.addOrReplaceChild("foot", CubeListBuilder.create().texOffs(0, 19).addBox(-2.0F, -5.0F, -2.0F, 7.0F, 5.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, 0.0F));

        PartDefinition head = snail.addOrReplaceChild("head", CubeListBuilder.create().texOffs(27, 21).addBox(-3.0F, -3.5F, -4.0F, 7.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -2.0F));

        PartDefinition left_eye = head.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(50, 19).addBox(-1.0F, -6.5F, -0.5F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, -2.0F, -3.0F, 0.4363F, 0.4363F, 0.4363F));

        PartDefinition right_eye = head.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(50, 19).addBox(0.0F, -6.5F, -0.4F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -2.0F, -3.0F, 0.4363F, -0.4363F, -0.4363F));

        PartDefinition right_tentacle = head.addOrReplaceChild("right_tentacle", CubeListBuilder.create().texOffs(50, 27).addBox(0.0F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.5F, -0.5F, -4.2F, 2.0071F, 0.0F, 0.4363F));

        PartDefinition left_tentacle = head.addOrReplaceChild("left_tentacle", CubeListBuilder.create().texOffs(50, 27).addBox(-1.0F, -1.5F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.4F, -0.5F, -4.2F, 2.0071F, 0.0F, -0.4363F));

        PartDefinition shell = snail.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -4.1F, 9.0F, 9.0F, 9.0F, new CubeDeformation(0.0F))
                .texOffs(37, 13).addBox(-4.0F, 0.0F, -5.1F, 9.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 3.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Snail entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        if(entity.isBaby()){
            this.animateWalk(SnailAnimations.SNAIL_WALK, limbSwing*3, limbSwingAmount*5, 2f, 2.5f);
        }else
            this.animateWalk(SnailAnimations.SNAIL_WALK, limbSwing*6, limbSwingAmount*5, 2f, 2.5f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -15f, 15f);
        headPitch = Mth.clamp(headPitch, -25f, 45f);

        this.head.yRot = headYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        snail.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public @NotNull ModelPart root() {
        return snail;
    }
}
