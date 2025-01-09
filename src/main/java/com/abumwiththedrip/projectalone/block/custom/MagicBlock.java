package com.abumwiththedrip.projectalone.block.custom;

import com.abumwiththedrip.projectalone.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

//If the detected item (flower, diamond) touches the block, then it turns into something else.

public class MagicBlock extends Block {
    protected static final VoxelShape COLLISION_SHAPE;
    protected static final VoxelShape OUTLINE_SHAPE;

    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hitResult) {
        level.playSound(player, pos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 1f, 1f);
        return InteractionResult.SUCCESS;
    }
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (!state.canSurvive(level, currentPos)) {
            level.scheduleTick(currentPos, this, 1);
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }
    // Check for items touching the block at any point, not just the top
    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);

        // Get the block's hitbox, enlarged
        AABB blockHitbox = state.getShape(level, pos).bounds().move(pos);
        blockHitbox = blockHitbox.inflate(0.1D, 0.1D, 0.1D); // Increase size for detection

        // Loop through all entities in the area of the block
        for (Entity entity : level.getEntitiesOfClass(ItemEntity.class, blockHitbox)) {
            if (entity instanceof ItemEntity itemEntity) {
                // Get the Y-coordinate of the entity
                double entityY = itemEntity.getY();

                // Check if the item is near the bottom of the block (adjust threshold as needed)
                if (entityY <= pos.getY() + 0.1D) {
                    // Handle item-specific logic for bottom detection
                    if (itemEntity.getItem().getItem() == ModItems.RAW_BISMUTH.get()) {
                        itemEntity.setItem(new ItemStack(Items.DIAMOND, itemEntity.getItem().getCount()));
                    }

                    if (itemEntity.getItem().getItem() == Items.DANDELION) {
                        itemEntity.setItem(new ItemStack(Items.WITHER_ROSE, itemEntity.getItem().getCount()));
                    }
                }
            }
        }
    }


    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (context instanceof EntityCollisionContext entityContext) {
            Entity entity = entityContext.getEntity();
            // Different collision shapes based on entity type
            if (entity instanceof Player) {
                return COLLISION_SHAPE; // Full collision for players
            } else if (entity instanceof ItemEntity) {
                return Block.box(0.1F, 0.1F, 0.1F, 15.9F, 15.9F, 15.9F); // A smaller collision for items (adjust as needed)
            }
        }
        return COLLISION_SHAPE; // Default collision
    }


    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return OUTLINE_SHAPE;
    }
    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            // Handle item interactions
            if(itemEntity.getItem().getItem() == ModItems.RAW_BISMUTH.get()) {
                itemEntity.setItem(new ItemStack(Items.DIAMOND, itemEntity.getItem().getCount()));
            }

            if(itemEntity.getItem().getItem() == Items.DANDELION) {
                itemEntity.setItem(new ItemStack(Items.WITHER_ROSE, itemEntity.getItem().getCount()));
            }
        }
    }


    protected boolean isPathfindable(BlockState state, PathComputationType pathComputationType) {
        return false;
    }

    static {
        COLLISION_SHAPE = Block.box((double)0F, (double)0F, (double)0F, (double)16.0F, (double)16.0F, (double)16.0F);
        OUTLINE_SHAPE = Block.box((double)0F, (double)0F, (double)0F, (double)16.0F, (double)16.0F, (double)16.0F);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.projectalone.magic_block.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
