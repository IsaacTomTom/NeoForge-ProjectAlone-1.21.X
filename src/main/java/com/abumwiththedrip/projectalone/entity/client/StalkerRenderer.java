/*package com.abumwiththedrip.projectalone.entity.client;

import com.abumwiththedrip.projectalone.ProjectAlone;
import com.abumwiththedrip.projectalone.entity.custom.StalkerEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class StalkerRenderer extends MobRenderer<StalkerEntity, StalkerModel<StalkerEntity>> {

    public StalkerRenderer(EntityRendererProvider.Context context) {
        super(context, new StalkerModel<>(context.bakeLayer(StalkerModel.LAYER_LOCATION)), 0);
    }

    @Override
    public ResourceLocation getTextureLocation(StalkerEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(ProjectAlone.MOD_ID, "textures/entity/stalker/stalker.png");
    }

    @Override
    public void render(StalkerEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0f,0f, 0f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}

 */
