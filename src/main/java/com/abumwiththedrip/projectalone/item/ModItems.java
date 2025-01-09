package com.abumwiththedrip.projectalone.item;

import com.abumwiththedrip.projectalone.ProjectAlone;
import com.abumwiththedrip.projectalone.entity.ModEntities;
import com.abumwiththedrip.projectalone.item.custom.ChiselItem;
import com.abumwiththedrip.projectalone.item.custom.FuelItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems { 
    public static final DeferredRegister.Items
            ITEMS = DeferredRegister.createItems(ProjectAlone.MOD_ID);

    public static final DeferredItem<Item> BISMUTH = ITEMS.register("bismuth",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));

    public static final DeferredItem<Item> RADISH = ITEMS.register("radish",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RADISH)) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
                    tooltipComponents.add(Component.translatable("tooltip.projectalone.radish.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });



    //First fuel way, new FuelItem using the FuelItem class, then put burn time here
    public static final DeferredItem<Item> FROSTFIRE_ICE = ITEMS.register("frostfire_ice",
            () -> new FuelItem(new Item.Properties(), 800));
    //Second way, regular item, see resources/data/neoforge/data_maps/item/furnace_fuels.json where its manually set
    public static final DeferredItem<Item> STARLIGHT_ASHES = ITEMS.register("starlight_ashes",
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
