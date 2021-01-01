package de.bulldog98.soulcapture

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot

class SoulCaptureEnchantment : Enchantment(
    Rarity.VERY_RARE,
    EnchantmentTarget.WEAPON,
    arrayOf(EquipmentSlot.MAINHAND)
) {
    override fun getMinLevel(): Int = 1
    override fun getMaxLevel(): Int = 1
}
