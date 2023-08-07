package net.nathanthecraziest.spawnersplus.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.nathanthecraziest.spawnersplus.SpawnersPlus;
import net.nathanthecraziest.spawnersplus.items.souls.ModSoulItem;

public class ModItems {

    public static final Item SPAWNER_FRAGMENT = registerItem("spawner_fragment", new Item(new FabricItemSettings()));
    public static final Item ZOMBIE_SOUL = registerItem("zombie_soul", new ModSoulItem(new FabricItemSettings(), EntityType.ZOMBIE));
    public static final Item SKELETON_SOUL = registerItem("skeleton_soul", new ModSoulItem(new FabricItemSettings(), EntityType.SKELETON));
    public static final Item SPIDER_SOUL = registerItem("spider_soul", new ModSoulItem(new FabricItemSettings(), EntityType.SPIDER));
    public static final Item CAVE_SPIDER_SOUL = registerItem("cave_spider_soul", new ModSoulItem(new FabricItemSettings(), EntityType.CAVE_SPIDER));
    public static final Item BLAZE_SOUL = registerItem("blaze_soul", new ModSoulItem(new FabricItemSettings(), EntityType.BLAZE));
    public static final Item MAGMA_CUBE_SOUL = registerItem("magma_cube_soul", new ModSoulItem(new FabricItemSettings(), EntityType.MAGMA_CUBE));
    public static final Item SILVERFISH_SOUL = registerItem("silverfish_soul", new ModSoulItem(new FabricItemSettings(), EntityType.SILVERFISH));
    public static final Item STRAY_SOUL = registerItem("stray_soul", new ModSoulItem(new FabricItemSettings(), EntityType.STRAY));
    public static final Item WITHER_SKELETON_SOUL = registerItem("wither_skeleton_soul", new ModSoulItem(new FabricItemSettings(), EntityType.WITHER_SKELETON));
    public static final Item HUSK_SOUL = registerItem("husk_soul", new ModSoulItem(new FabricItemSettings(), EntityType.HUSK));
    public static final Item DROWNED_SOUL = registerItem("drowned_soul", new ModSoulItem(new FabricItemSettings(), EntityType.DROWNED));

    public static final Item SPAWNER_SILENCER = registerItem("spawner_silencer", new Item(new FabricItemSettings().rarity(Rarity.UNCOMMON)));
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(SpawnersPlus.MOD_ID, name), item);
    }

    public static void registerModItems(){
        SpawnersPlus.LOGGER.debug("Registering Mod Items for " + SpawnersPlus.MOD_ID);
    }
}
