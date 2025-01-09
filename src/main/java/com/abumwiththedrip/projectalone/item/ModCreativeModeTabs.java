package com.abumwiththedrip.projectalone.item;

import com.abumwiththedrip.projectalone.ProjectAlone;
import com.abumwiththedrip.projectalone.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ProjectAlone.MOD_ID);

    public static final Supplier<CreativeModeTab> BISMUTH_ITEMS_TAB = CREATIVE_MODE_TAB.register("bismuth_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BISMUTH.get()))
                    .title(Component.translatable("creativetab.projectalone.bismuth_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.BISMUTH);
                        output.accept(ModItems.RAW_BISMUTH);

                        output.accept(ModItems.CHISEL);
                    }).build());

    public static final Supplier<CreativeModeTab> BISMUTH_BLOCK_TAB = CREATIVE_MODE_TAB.register("bismuth_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BISMUTH_BLOCK.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ProjectAlone.MOD_ID, "bismuth_items_tab"))
                    .title(Component.translatable("creativetab.projectalone.bismuth_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.BISMUTH_BLOCK);
                        output.accept(ModBlocks.BISMUTH_ORE);
                        output.accept(ModBlocks.BISMUTH_DEEPSLATE_ORE);
                        output.accept(ModBlocks.MAGIC_BLOCK);

                    }).build());

    public static final Supplier<CreativeModeTab> BISMUTH_FOOD_TAB = CREATIVE_MODE_TAB.register("bismuth_foods_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RADISH.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ProjectAlone.MOD_ID, "bismuth_blocks_tab"))
                    .title(Component.translatable("creativetab.projectalone.bismuth_foods"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(ModItems.RADISH);
                    }).build());

    public static final Supplier<CreativeModeTab> BISMUTH_INGREDIENTS_TAB = CREATIVE_MODE_TAB.register("bismuth_ingredients_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.FROSTFIRE_ICE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ProjectAlone.MOD_ID, "bismuth_foods_tab"))
                    .title(Component.translatable("creativetab.projectalone.bismuth_ingredients"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.FROSTFIRE_ICE);
                        output.accept(ModItems.STARLIGHT_ASHES);
                    }).build());

    public static final Supplier<CreativeModeTab> PROJECTALONE_MOB_TAB = CREATIVE_MODE_TAB.register("projectalone_mob_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BISMUTH_BLOCK))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ProjectAlone.MOD_ID, "bismuth_items_tab"))
                    .title(Component.translatable("creativetab.projectalone.projectalone_mobs"))
                    .displayItems((itemDisplayParameters, output) -> {
                       /*
                        output.accept(ModItems.STALKER_SPAWN_EGG);
                        output.accept(ModItems.GECKO_SPAWN_EGG);

                        */
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}