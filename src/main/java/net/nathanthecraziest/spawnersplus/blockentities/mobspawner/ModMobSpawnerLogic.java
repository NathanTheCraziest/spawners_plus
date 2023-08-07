package net.nathanthecraziest.spawnersplus.blockentities.mobspawner;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.MobSpawnerEntry;
import net.minecraft.world.MobSpawnerLogic;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModMobSpawnerLogic extends MobSpawnerLogic {


    @Override
    public void sendStatus(World world, BlockPos pos, int status) {

    }

    @Override
    public void setSpawnEntry(@Nullable World world, BlockPos pos, MobSpawnerEntry spawnEntry) {
        super.setSpawnEntry(world, pos, spawnEntry);
    }
}
