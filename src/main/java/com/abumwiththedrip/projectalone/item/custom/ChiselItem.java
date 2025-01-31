package com.abumwiththedrip.projectalone.item.custom;

import com.abumwiththedrip.projectalone.block.ModBlocks;
import com.abumwiththedrip.projectalone.component.ModDataComponents;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Map;
import java.util.AbstractMap;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.ofEntries(
                    new AbstractMap.SimpleEntry<>(Blocks.STONE, Blocks.STONE_BRICKS),
                    new AbstractMap.SimpleEntry<>(Blocks.END_STONE, Blocks.END_STONE_BRICKS),
                    new AbstractMap.SimpleEntry<>(Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS),
                    new AbstractMap.SimpleEntry<>(Blocks.GOLD_BLOCK, Blocks.IRON_BLOCK),
                    new AbstractMap.SimpleEntry<>(Blocks.IRON_BLOCK, Blocks.STONE),
                    new AbstractMap.SimpleEntry<>(Blocks.NETHERRACK, ModBlocks.BISMUTH_BLOCK.get())
            );

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                        item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);

                context.getItemInHand().set(ModDataComponents.COORDINATES, context.getClickedPos());

            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.projectalone.chisel.shift_down"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.projectalone.chisel"));
        }

        if(stack.get(ModDataComponents.COORDINATES) != null) {
            tooltipComponents.add(Component.literal("Last Block changed at\n" + stack.get(ModDataComponents.COORDINATES)));
        }

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

    }
}