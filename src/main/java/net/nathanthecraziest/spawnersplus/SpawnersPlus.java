package net.nathanthecraziest.spawnersplus;

import net.fabricmc.api.ModInitializer;
import net.nathanthecraziest.spawnersplus.blocks.ModBlocks;
import net.nathanthecraziest.spawnersplus.items.ModEnchantments;
import net.nathanthecraziest.spawnersplus.items.ModItemGroup;
import net.nathanthecraziest.spawnersplus.items.ModItems;
import net.nathanthecraziest.spawnersplus.util.ModLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpawnersPlus implements ModInitializer {
	public static final String MOD_ID = "spawnersplus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroup.registerModItemGroups();
		ModEnchantments.registerModEnchantments();
		ModLootTableModifiers.modifyLootTables();
	}
}
