package net.nathanthecraziest.spawnersplus.items.souls;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerEntry;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.nathanthecraziest.spawnersplus.blockentities.mobspawner.ModMobSpawnerLogic;
import net.nathanthecraziest.spawnersplus.blocks.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class ModSoulItem extends Item {

    private final EntityType<?> type;

    public EntityType<?> getEntityType(@Nullable NbtCompound nbt) {
        if (nbt != null && nbt.contains("EntityTag", 10)) {
            NbtCompound nbtCompound = nbt.getCompound("EntityTag");
            if (nbtCompound.contains("id", 8)) {
                return (EntityType)EntityType.get(nbtCompound.getString("id")).orElse(this.type);
            }
        }

        return this.type;
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.UNCOMMON;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {

        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        ItemStack itemStack = context.getStack();
        BlockState blockState = world.getBlockState(blockPos);

        if (world.getBlockState(blockPos).getBlock() == ModBlocks.INACTIVE_SPAWNER){

            world.setBlockState(blockPos, Blocks.SPAWNER.getDefaultState());
            MobSpawnerBlockEntity spawnerBlockEntity = new MobSpawnerBlockEntity(blockPos, Blocks.SPAWNER.getDefaultState());

            BlockEntity blockEntity = world.getBlockEntity(blockPos);
            if (blockEntity instanceof MobSpawnerBlockEntity) {
                MobSpawnerBlockEntity mobSpawnerBlockEntity = (MobSpawnerBlockEntity)blockEntity;
                EntityType<?> entityType = this.getEntityType(itemStack.getNbt());
                mobSpawnerBlockEntity.setEntityType(entityType, world.getRandom());
                blockEntity.markDirty();
                world.updateListeners(blockPos, blockState, blockState, 3);
                world.emitGameEvent(context.getPlayer(), GameEvent.BLOCK_CHANGE, blockPos);
                itemStack.decrement(1);

                world.playSound(null, blockPos, SoundEvents.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.NEUTRAL, 1f, 1f);
                context.getPlayer().swingHand(context.getHand());

                return ActionResult.CONSUME;
            }
        }

        return super.useOnBlock(context);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public ModSoulItem(FabricItemSettings settings, EntityType<?> type) {
        super(settings);
        this.type = type;
    }
}
