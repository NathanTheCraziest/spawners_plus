package net.nathanthecraziest.spawnersplus.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class SpawnersPlusConfig {

    private static final HashMap<String, Float> DROP_RATE = new HashMap<>();

    public static float getFloatValue(String key) {
        if (!DROP_RATE.containsKey(key)) {
            System.out.println(key);
        }
        return DROP_RATE.getOrDefault(key, null);
    }

    public static void init() {
        DROP_RATE.put("zombie_soul", 0.04f);
        DROP_RATE.put("skeleton_soul", 0.04f);
        DROP_RATE.put("spider_soul", 0.04f);
        DROP_RATE.put("cave_spider_soul", 0.07f);
        DROP_RATE.put("blaze_soul", 0.05f);
        DROP_RATE.put("magma_cube_soul", 0.02f);
        DROP_RATE.put("stray_soul", 0.00f);
        DROP_RATE.put("wither_skeleton_soul", 0.00f);
        DROP_RATE.put("husk_soul", 0.04f);
        DROP_RATE.put("drowned_soul", 0.00f);
        DROP_RATE.put("creeper_soul", 0.05f);
    }

    public static void loadConfig() {
        JsonObject json;
        json = Config.getJsonObject(Config.readFile(new File("config/spawnersplus/soul_drop_rates.json")));
        for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
            DROP_RATE.put(entry.getKey(), entry.getValue().getAsFloat());
        }
    }

    public static void generateConfigs(boolean overwrite) {
        StringBuilder config = new StringBuilder("{\n");
        int i = 0;
        for (String key : DROP_RATE.keySet()) {
            config.append("  \"").append(key).append("\": ").append(DROP_RATE.get(key));
            ++i;
            if (i < DROP_RATE.size()) {
                config.append(",");
            }
            config.append("\n");
        }
        config.append("}");
        Config.createFile("config/spawnersplus/soul_drop_rates.json", config.toString(), overwrite);
    }
}
