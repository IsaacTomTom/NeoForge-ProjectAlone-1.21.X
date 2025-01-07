package com.abumwiththedrip.projectalone.event;

import com.abumwiththedrip.projectalone.ProjectAlone;
import com.abumwiththedrip.projectalone.entity.ModEntities;
import com.abumwiththedrip.projectalone.entity.client.StalkerModel;
import com.abumwiththedrip.projectalone.entity.custom.StalkerEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = ProjectAlone.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(StalkerModel.LAYER_LOCATION, StalkerModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.STALKER.get(), StalkerEntity.createAttributes().build());
    }
}
