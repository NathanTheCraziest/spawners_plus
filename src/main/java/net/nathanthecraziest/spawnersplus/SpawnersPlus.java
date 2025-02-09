package net.nathanthecraziest.spawnersplus;

import com.google.gson.JsonObject;
import net.fabricmc.api.ModInitializer;
import net.nathanthecraziest.spawnersplus.blocks.ModBlocks;
import net.nathanthecraziest.spawnersplus.config.Config;
import net.nathanthecraziest.spawnersplus.config.SpawnersPlusConfig;
import net.nathanthecraziest.spawnersplus.items.ModEnchantments;
import net.nathanthecraziest.spawnersplus.items.ModItemGroup;
import net.nathanthecraziest.spawnersplus.items.ModItems;
import net.nathanthecraziest.spawnersplus.util.ModLootTableModifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class SpawnersPlus implements ModInitializer {
	public static final String MOD_ID = "spawnersplus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		SpawnersPlusConfig.init();
		String defaultConfig = "{\n" + "  \"reset_configs_on_startup\": false\n" + "}";
		File configFile = Config.createFile("config/spawnersplus/reset_config.json", defaultConfig, false);
		JsonObject json = Config.getJsonObject(Config.readFile(configFile));
		SpawnersPlusConfig.generateConfigs(json == null || !json.has("reset_configs_on_startup") || json.get("reset_configs_on_startup").getAsBoolean());
		SpawnersPlusConfig.loadConfig();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModItemGroup.registerModItemGroups();
		ModEnchantments.registerModEnchantments();
		ModLootTableModifiers.modifyLootTables();
	}
}
