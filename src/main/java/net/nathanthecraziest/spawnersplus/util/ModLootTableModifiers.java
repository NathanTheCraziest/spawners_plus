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


    public static void modifyLootTables(){
        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {

            if(source.isBuiltin() && ZOMBIE_ID.equals(id)) addMobSoulDrop(ModItems.ZOMBIE_SOUL, 0.02f, tableBuilder);
            if(source.isBuiltin() && SKELETON_ID.equals(id)) addMobSoulDrop(ModItems.SKELETON_SOUL, 0.02f, tableBuilder);
            if(source.isBuiltin() && SPIDER_ID.equals(id)) addMobSoulDrop(ModItems.SPIDER_SOUL, 0.02f, tableBuilder);
            if(source.isBuiltin() && CAVE_SPIDER_ID.equals(id)) addMobSoulDrop(ModItems.CAVE_SPIDER_SOUL, 0.05f, tableBuilder);
            if(source.isBuiltin() && BLAZE_ID.equals(id)) addMobSoulDrop(ModItems.BLAZE_SOUL, 0.03f, tableBuilder);
            if(source.isBuiltin() && MAGMA_CUBE_ID.equals(id)) addMobSoulDrop(ModItems.MAGMA_CUBE_SOUL, 0.01f, tableBuilder);
            if(source.isBuiltin() && HUSK_ID.equals(id)) addMobSoulDrop(ModItems.HUSK_SOUL, 0.02f, tableBuilder);
        }));
    }

    public static void addMobSoulDrop(Item soulItem, float soulDropChance, FabricLootTableBuilder tableBuilder){
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
