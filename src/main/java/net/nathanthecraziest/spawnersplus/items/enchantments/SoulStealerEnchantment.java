package net.nathanthecraziest.spawnersplus.items.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.*;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.nathanthecraziest.spawnersplus.items.ModItems;
import org.apache.logging.log4j.core.appender.ScriptAppenderSelector;

public class SoulStealerEnchantment extends Enchantment {
    public SoulStealerEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot... slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return 15;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
