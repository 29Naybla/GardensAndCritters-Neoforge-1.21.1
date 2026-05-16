package com.x29naybla.gardens_and_critters.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.x29naybla.gardens_and_critters.GardensandCritters;
import com.x29naybla.gardens_and_critters.client.animations.SnailAnimations;
import com.x29naybla.gardens_and_critters.common.entity.Snail;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class SnailModel<T extends Snail> extends HierarchicalModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(GardensandCritters.MODID, "snail"), "main");
    private final ModelPart snail;
    private final ModelPart foot;
    private final ModelPart head;
    private final ModelPart left_eye;
    private final ModelPart right_eye;
    private final ModelPart right_tentacle;
    private final ModelPart left_tentacle;
    private final ModelPart shell;

    public SnailModel(ModelPart root) {
        this.snail = root.getChild("snail");
        this.foot = this.snail.getChild("foot");
        this.head = this.snail.getChild("head");
        this.left_eye = this.head.getChild("left_eye");
        this.right_eye = this.head.getChild("right_eye");
        this.right_tentacle = this.head.getChild("right_tentacle");
        this.left_tentacle = this.head.getChild("left_tentacle");
        this.shell = this.snail.getChild("shell");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition snail = partdefinition.addOrReplaceChild("snail", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -1.0F));

        PartDefinition foot = snail.addOrReplaceChild("foot", CubeListBuilder.create().texOffs(0, 19).addBox(-1.0F, -4.0F, -2.0F, 5.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, 0.0F));

        PartDefinition head = snail.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 21).addBox(-2.0F, -2.5F, -4.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -2.0F));

        PartDefinition left_eye = head.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(6, -1).addBox(0.0F, -5.5F, -0.5F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -1.0F, -3.0F, 0.4363F, 0.4363F, 0.4363F));

        PartDefinition right_eye = head.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(3, -1).addBox(0.0F, -5.5F, -0.4F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, -3.0F, 0.4363F, -0.4363F, -0.4363F));

        PartDefinition right_tentacle = head.addOrReplaceChild("right_tentacle", CubeListBuilder.create().texOffs(3, 5).addBox(0.0F, -1.5F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, -0.5F, -4.2F, 2.0071F, 0.0F, 0.4363F));

        PartDefinition left_tentacle = head.addOrReplaceChild("left_tentacle", CubeListBuilder.create().texOffs(6, 5).addBox(0.0F, -1.5F, -0.5F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.4F, -0.5F, -4.2F, 2.0071F, 0.0F, -0.4363F));

        PartDefinition shell = snail.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.0F, -4.0F, 6.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, 3.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(Snail entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(SnailAnimations.SNAIL_WALK, limbSwing, limbSwingAmount, 8f, 40f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45f);

        this.head.yRot = headYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        snail.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return snail;
    }
}
