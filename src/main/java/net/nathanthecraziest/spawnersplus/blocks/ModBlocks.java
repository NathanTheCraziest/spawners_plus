package net.nathanthecraziest.spawnersplus.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.nathanthecraziest.spawnersplus.SpawnersPlus;

public class ModBlocks {

    public static final Block INACTIVE_SPAWNER = registerBlock("inactive_spawner",
            new Block(FabricBlockSettings.create().requiresTool().strength(5.0f).sounds(BlockSoundGroup.METAL).nonOpaque()));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(SpawnersPlus.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        Item item = Registry.register(Registries.ITEM, new Identifier(SpawnersPlus.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        return item;
    }

    public static void registerModBlocks(){
        SpawnersPlus.LOGGER.debug("Registering Mod Blocks for " + SpawnersPlus.MOD_ID);
    }
}
