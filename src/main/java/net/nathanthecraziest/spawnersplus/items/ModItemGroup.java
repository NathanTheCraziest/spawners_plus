package net.nathanthecraziest.spawnersplus.items;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.nathanthecraziest.spawnersplus.blocks.ModBlocks;

public class ModItemGroup {

    public static void registerModItemGroups(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
            content.addAfter(ModBlocks.INACTIVE_SPAWNER, ModItems.SPAWNER_FRAGMENT);
            content.addAfter(ModItems.SPAWNER_FRAGMENT, ModItems.SPAWNER_SILENCER);
            content.addAfter(ModItems.SPAWNER_SILENCER, ModItems.ZOMBIE_SOUL);
            content.addAfter(ModItems.ZOMBIE_SOUL, ModItems.SKELETON_SOUL);
            content.addAfter(ModItems.SKELETON_SOUL, ModItems.SPIDER_SOUL);
            content.addAfter(ModItems.SPIDER_SOUL, ModItems.CAVE_SPIDER_SOUL);
            content.addAfter(ModItems.CAVE_SPIDER_SOUL, ModItems.BLAZE_SOUL);
            content.addAfter(ModItems.BLAZE_SOUL, ModItems.MAGMA_CUBE_SOUL);
            content.addAfter(ModItems.MAGMA_CUBE_SOUL, ModItems.STRAY_SOUL);
            content.addAfter(ModItems.STRAY_SOUL, ModItems.WITHER_SKELETON_SOUL);
            content.addAfter(ModItems.WITHER_SKELETON_SOUL, ModItems.HUSK_SOUL);
            content.addAfter(ModItems.HUSK_SOUL, ModItems.DROWNED_SOUL);
        });
    }
}
