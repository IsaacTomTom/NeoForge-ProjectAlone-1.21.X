package com.abumwiththedrip.projectalone;

import com.abumwiththedrip.projectalone.block.ModBlocks;
import com.abumwiththedrip.projectalone.entity.ModEntities;
/*import com.abumwiththedrip.projectalone.entity.client.GeckoRenderer;
import com.abumwiththedrip.projectalone.entity.client.StalkerRenderer;

 */
import com.abumwiththedrip.projectalone.item.ModCreativeModeTabs;
import com.abumwiththedrip.projectalone.item.ModItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ProjectAlone.MOD_ID)
public class ProjectAlone
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "projectalone";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public ProjectAlone(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);


        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.BISMUTH);
            event.accept(ModItems.RAW_BISMUTH);
        }

        if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
            event.accept(ModItems.RADISH);
        }

        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.FROSTFIRE_ICE);
            event.accept(ModItems.STARLIGHT_ASHES);
        }

        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.BISMUTH_BLOCK);
            event.accept(ModBlocks.BISMUTH_ORE);
            event.accept(ModBlocks.BISMUTH_DEEPSLATE_ORE);
            event.accept(ModBlocks.MAGIC_BLOCK);

            event.accept(ModBlocks.BISMUTH_STAIRS);
            event.accept(ModBlocks.BISMUTH_SLAB);

            event.accept(ModBlocks.BISMUTH_PRESSURE_PLATE);
            event.accept(ModBlocks.BISMUTH_BUTTON);

            event.accept(ModBlocks.BISMUTH_FENCE);
            event.accept(ModBlocks.BISMUTH_FENCE_GATE);
            event.accept(ModBlocks.BISMUTH_WALL);

            event.accept(ModBlocks.BISMUTH_DOOR);
            event.accept(ModBlocks.BISMUTH_TRAPDOOR);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {

        }
    }
}
 /*EntityRenderers.register(ModEntities.STALKER.get(), StalkerRenderer::new);
            EntityRenderers.register(ModEntities.GECKO.get(), GeckoRenderer::new);

             */