package net.nathanthecraziest.spawnersplus;

import com.google.gson.JsonObject;

import de.tomalbrc.filament.api.FilamentLoader;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.nathanthecraziest.spawnersplus.config.Config;
import net.nathanthecraziest.spawnersplus.config.SpawnersPlusConfig;
import net.nathanthecraziest.spawnersplus.util.ModLootTableModifiers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class SpawnersPlus implements ModInitializer {
	public static final String MOD_ID = "spawnersplus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final RegistryKey<Enchantment> SOUL_STEALING = RegistryKey.of(RegistryKeys.ENCHANTMENT,
			Identifier.of("spawnersplus", "soul_stealing"));

	@Override
	public void onInitialize() {
		SpawnersPlusConfig.init();
		String defaultConfig = "{\n" + "  \"reset_configs_on_startup\": false\n" + "}";
		File configFile = Config.createFile("config/spawnersplus/reset_config.json", defaultConfig, false);
		JsonObject json = Config.getJsonObject(Config.readFile(configFile));
		SpawnersPlusConfig.generateConfigs(json == null || !json.has("reset_configs_on_startup")
				|| json.get("reset_configs_on_startup").getAsBoolean());
		SpawnersPlusConfig.loadConfig();

		FilamentLoader.loadModels(MOD_ID, MOD_ID);
		FilamentLoader.loadItems(MOD_ID);
		FilamentLoader.loadBlocks(MOD_ID);
		PolymerResourcePackUtils.addModAssets(MOD_ID);
		PolymerResourcePackUtils.markAsRequired();

		UseBlockCallback.EVENT.register(new InactiveSpawnerHandler());
		ModLootTableModifiers.modifyLootTables();
	}
}
