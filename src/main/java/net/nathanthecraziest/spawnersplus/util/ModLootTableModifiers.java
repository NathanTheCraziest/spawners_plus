package net.nathanthecraziest.spawnersplus.util;

import net.fabricmc.fabric.api.loot.v3.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
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
import net.minecraft.predicate.item.EnchantmentsPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.predicate.item.ItemSubPredicateTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.nathanthecraziest.spawnersplus.SpawnersPlus;
import net.nathanthecraziest.spawnersplus.config.SpawnersPlusConfig;

public class ModLootTableModifiers {

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (source.isBuiltin()) {
                RegistryEntryLookup<Enchantment> enchantmentLookup = registries
                        .getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
                RegistryEntry<Enchantment> soulStealerEntry = enchantmentLookup.getOrThrow(SpawnersPlus.SOUL_STEALING);

                // Add soul drop to each mob
                if (EntityType.ZOMBIE.getLootTableId().equals(key)) // Zombie
                    addMobSoulDrop("zombie", tableBuilder, soulStealerEntry);
                if (EntityType.SKELETON.getLootTableId().equals(key)) // Skeleton
                    addMobSoulDrop("skeleton", tableBuilder, soulStealerEntry);
                if (EntityType.SPIDER.getLootTableId().equals(key)) // Spider
                    addMobSoulDrop("spider", tableBuilder, soulStealerEntry);
                if (EntityType.CAVE_SPIDER.getLootTableId().equals(key)) // Cave Spider
                    addMobSoulDrop("cave_spider", tableBuilder, soulStealerEntry);
                if (EntityType.BLAZE.getLootTableId().equals(key)) // Blaze
                    addMobSoulDrop("blaze", tableBuilder, soulStealerEntry);
                if (EntityType.MAGMA_CUBE.getLootTableId().equals(key)) // Magma Cube
                    addMobSoulDrop("magma_cube", tableBuilder, soulStealerEntry);
                if (EntityType.STRAY.getLootTableId().equals(key)) // Stray
                    addMobSoulDrop("stray", tableBuilder, soulStealerEntry);
                if (EntityType.WITHER_SKELETON.getLootTableId().equals(key)) // Wither Skeleton
                    addMobSoulDrop("wither_skeleton", tableBuilder, soulStealerEntry);
                if (EntityType.HUSK.getLootTableId().equals(key)) // Husk
                    addMobSoulDrop("husk", tableBuilder, soulStealerEntry);
                if (EntityType.DROWNED.getLootTableId().equals(key)) // Droned
                    addMobSoulDrop("drowned", tableBuilder, soulStealerEntry);
                if (EntityType.CREEPER.getLootTableId().equals(key)) // Creeper
                    addMobSoulDrop("creeper", tableBuilder, soulStealerEntry);
            }
        });
    }

    public static void addMobSoulDrop(String type, FabricLootTableBuilder tableBuilder,
            RegistryEntry<Enchantment> enchantement) {
        float soulDropChance = getConfigDropRate(type);
        Item soulItem = Registries.ITEM.get(Identifier.of("spawnersplus:" + type + "_soul"));

        if (soulDropChance > 0f) {
            LootPool.Builder poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .conditionally(RandomChanceLootCondition.builder(soulDropChance))
                    .conditionally(EntityPropertiesLootCondition.builder(LootContext.EntityTarget.ATTACKER,
                            new EntityPredicate.Builder().equipment(EntityEquipmentPredicate.Builder.create()
                                    .mainhand(ItemPredicate.Builder.create()
                                            .subPredicate(ItemSubPredicateTypes.ENCHANTMENTS,
                                                    EnchantmentsPredicate.enchantments(java.util.List.of(
                                                            new EnchantmentPredicate(enchantement,
                                                                    NumberRange.IntRange.ANY))))))
                                    .build())
                            .build())
                    .with(ItemEntry.builder(soulItem))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1f, 1f)).build());
            tableBuilder.pool(poolBuilder.build());
        }

    }

    public static float getConfigDropRate(String key) {
        float rate = SpawnersPlusConfig.getFloatValue(key + "_soul");
        if (rate <= 1f)
            return rate;
        else
            return 1f;
    }
}
