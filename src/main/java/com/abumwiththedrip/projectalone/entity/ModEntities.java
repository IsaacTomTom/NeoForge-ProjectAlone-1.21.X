package com.abumwiththedrip.projectalone.entity;

import com.abumwiththedrip.projectalone.ProjectAlone;
import com.abumwiththedrip.projectalone.entity.custom.GeckoEntity;
import com.abumwiththedrip.projectalone.entity.custom.StalkerEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, ProjectAlone.MOD_ID);

    public static final Supplier<EntityType<StalkerEntity>> STALKER =
            ENTITY_TYPES.register("stalker", () -> EntityType.Builder.of(StalkerEntity::new, MobCategory.CREATURE)
                    .sized(.075f, 0.35f).build("stalker"));

    public static final Supplier<EntityType<GeckoEntity>> GECKO =
            ENTITY_TYPES.register("gecko", () -> EntityType.Builder.of(GeckoEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.35f).build("gecko"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
