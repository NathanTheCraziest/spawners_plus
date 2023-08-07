package net.nathanthecraziest.spawnersplus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.nathanthecraziest.spawnersplus.blocks.ModBlocks;

public class SpawnersPlusClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INACTIVE_SPAWNER, RenderLayer.getCutoutMipped());
    }
}
