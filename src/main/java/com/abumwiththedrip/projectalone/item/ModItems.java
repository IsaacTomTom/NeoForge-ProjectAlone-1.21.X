package com.abumwiththedrip.projectalone.item;

import com.abumwiththedrip.projectalone.ProjectAlone;
import com.abumwiththedrip.projectalone.entity.ModEntities;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems { 
    public static final DeferredRegister.Items
            ITEMS = DeferredRegister.createItems(ProjectAlone.MOD_ID);

    public static final DeferredItem<Item> BISMUTH = ITEMS.register("bismuth",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> STALKER_SPAWN_EGG = ITEMS.register("stalker_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.STALKER, 0x31afaf, 0xffac00,
                    new Item.Properties()));

    public static final DeferredItem<Item> GECKO_SPAWN_EGG = ITEMS.register("gecko_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.GECKO, 0x31afaf, 0xffac00,
                    new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
   }
}
