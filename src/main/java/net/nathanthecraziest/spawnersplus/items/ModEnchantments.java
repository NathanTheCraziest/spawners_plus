package net.nathanthecraziest.spawnersplus.items;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nathanthecraziest.spawnersplus.SpawnersPlus;
import net.nathanthecraziest.spawnersplus.items.enchantments.SoulStealerEnchantment;

public class ModEnchantments {

    public static Enchantment SOUL_STEALING = register(new SoulStealerEnchantment(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}), "soul_stealing");

    public static Enchantment register(Enchantment enchantment, String name){
        return Registry.register(Registries.ENCHANTMENT, new Identifier(SpawnersPlus.MOD_ID, name), enchantment);
    }

    public static void registerModEnchantments(){
        SpawnersPlus.LOGGER.debug("Registering Mod Enchantments for " + SpawnersPlus.MOD_ID);
    }
}
