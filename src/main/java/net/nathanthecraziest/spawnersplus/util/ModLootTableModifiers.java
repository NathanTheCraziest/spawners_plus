package net.nathanthecraziest.spawnersplus.util;

import net.fabricmc.fabric.api.loot.v2.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityEquipmentPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.item.EnchantmentPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import net.nathanthecraziest.spawnersplus.SpawnersPlus;
import net.nathanthecraziest.spawnersplus.config.SpawnersPlusConfig;
import net.nathanthecraziest.spawnersplus.items.ModEnchantments;
import net.nathanthecraziest.spawnersplus.items.ModItems;

public class ModLootTableModifiers {
    public static final Identifier ZOMBIE_ID = EntityType.ZOMBIE.getLootTableId();
    public static final Identifier SKELETON_ID = EntityType.SKELETON.getLootTableId();
    public static final Identifier SPIDER_ID = EntityType.SPIDER.getLootTableId();
    public static final Identifier CAVE_SPIDER_ID = EntityType.CAVE_SPIDER.getLootTableId();
    public static final Identifier BLAZE_ID = EntityType.BLAZE.getLootTableId();
    public static final Identifier MAGMA_CUBE_ID = EntityType.MAGMA_CUBE.getLootTableId();
    public static final Identifier STRAY_ID = EntityType.STRAY.getLootTableId();
    public static final Identifier WITHER_SKELETON_ID = EntityType.WITHER_SKELETON.getLootTableId();
    public static final Identifier HUSK_ID = EntityType.HUSK.getLootTableId();
    public static final Identifier DROWNED_ID = EntityType.DROWNED.getLootTableId();
    public static final Identifier CREEPER_ID = EntityType.CREEPER.getLootTableId();


    public static void modifyLootTables(){
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {

            if(source.isBuiltin() && ZOMBIE_ID.equals(id)) addMobSoulDrop(ModItems.ZOMBIE_SOUL, getConfigDropRate("zombie"), tableBuilder);
            if(source.isBuiltin() && SKELETON_ID.equals(id)) addMobSoulDrop(ModItems.SKELETON_SOUL, getConfigDropRate("skeleton"), tableBuilder);
            if(source.isBuiltin() && SPIDER_ID.equals(id)) addMobSoulDrop(ModItems.SPIDER_SOUL, getConfigDropRate("spider"), tableBuilder);
            if(source.isBuiltin() && CAVE_SPIDER_ID.equals(id)) addMobSoulDrop(ModItems.CAVE_SPIDER_SOUL, getConfigDropRate("cave_spider"), tableBuilder);
            if(source.isBuiltin() && BLAZE_ID.equals(id)) addMobSoulDrop(ModItems.BLAZE_SOUL, getConfigDropRate("blaze"), tableBuilder);
            if(source.isBuiltin() && MAGMA_CUBE_ID.equals(id)) addMobSoulDrop(ModItems.MAGMA_CUBE_SOUL, getConfigDropRate("magma_cube"), tableBuilder);
            if(source.isBuiltin() && STRAY_ID.equals(id)) addMobSoulDrop(ModItems.STRAY_SOUL, getConfigDropRate("stray"), tableBuilder);
            if(source.isBuiltin() && WITHER_SKELETON_ID.equals(id)) addMobSoulDrop(ModItems.WITHER_SKELETON_SOUL, getConfigDropRate("wither_skeleton"), tableBuilder);
            if(source.isBuiltin() && HUSK_ID.equals(id)) addMobSoulDrop(ModItems.HUSK_SOUL, getConfigDropRate("husk"), tableBuilder);
            if(source.isBuiltin() && DROWNED_ID.equals(id)) addMobSoulDrop(ModItems.DROWNED_SOUL, getConfigDropRate("drowned"), tableBuilder);
            if(source.isBuiltin() && CREEPER_ID.equals(id)) addMobSoulDrop(ModItems.CREEPER_SOUL, getConfigDropRate("creeper"), tableBuilder);
        }));
    }

    public static void addMobSoulDrop(Item soulItem, float soulDropChance, FabricLootTableBuilder tableBuilder){
        if(soulDropChance > 0) {
            LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(soulDropChance))
                    .conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.KILLER,
                            new EntityPredicate.Builder().equipment(EntityEquipmentPredicate.Builder.create()
                                    .mainhand(ItemPredicate.Builder.create()
                                            .enchantment(new EnchantmentPredicate(ModEnchantments.SOUL_STEALING, NumberRange.IntRange.ANY)).build()).build()).build()))
                    .with(ItemEntry.builder(soulItem))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f,1f)).build());
            tableBuilder.pool(poolBuilder.build());
        }
    }

    public static float getConfigDropRate(String key){
        float rate = SpawnersPlusConfig.getFloatValue(key + "_soul");
        if (rate <= 1f) return rate;
        else return 1f;
    }
}
