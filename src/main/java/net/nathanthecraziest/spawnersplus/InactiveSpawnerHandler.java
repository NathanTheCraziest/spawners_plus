package net.nathanthecraziest.spawnersplus;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;

public class InactiveSpawnerHandler implements UseBlockCallback {
    private static final Pattern SOUL_PATTERN = Pattern.compile("spawnersplus:(.*)_soul");

    @Override
    public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
        if (world.isClient) {
            return ActionResult.PASS;
        }

        // Prevent interaction in adventure mode
        if (player instanceof ServerPlayerEntity serverPlayer) {
            if (serverPlayer.interactionManager.getGameMode() == GameMode.ADVENTURE) {
                return ActionResult.PASS;
            }
        }

        BlockPos pos = hitResult.getBlockPos();
        Identifier blockID = Registries.BLOCK.getId(world.getBlockState(pos).getBlock());

        // Check if block is the correct one
        if (!blockID.toString().contains("inactive_spawner")) {
            return ActionResult.PASS;
        }

        // Retrieve item from active hand
        ItemStack handStack = player.getStackInHand(player.getActiveHand());
        Identifier itemID = Registries.ITEM.getId(handStack.getItem());
        Matcher matcher = SOUL_PATTERN.matcher(itemID.toString());

        if (!matcher.matches()) {
            return ActionResult.PASS;
        }

        // Replace block to spawner
        world.setBlockState(pos, Blocks.SPAWNER.getDefaultState());
        MobSpawnerBlockEntity mbe = (MobSpawnerBlockEntity) world.getBlockEntity(pos);
        Identifier entityID = Identifier.of("minecraft", matcher.group(1));
        EntityType<?> type = Registries.ENTITY_TYPE.get(entityID);

        mbe.getLogic().setEntityId(type, world, world.getRandom(), pos);
        mbe.markDirty();
        world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 3);

        if (!player.getAbilities().creativeMode)
            handStack.decrement(1);

        // Play sound effect
        world.playSound(null, pos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);

        return ActionResult.SUCCESS;
    }
}
