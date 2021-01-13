package de.bulldog98.soulcapture

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.SpawnEggItem
import net.minecraft.server.world.ServerWorld
import java.util.Random
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class SoulCaptureEnchantment : Enchantment(
    Rarity.VERY_RARE,
    EnchantmentTarget.WEAPON,
    arrayOf(EquipmentSlot.MAINHAND)
) {
    override fun getMinLevel(): Int = 1
    override fun getMaxLevel(): Int = 1
    override fun getMinPower(level: Int): Int =
        15
    override fun getMaxPower(level: Int): Int =
        super.getMinPower(level) + 50

    fun handleEnchantment(world: ServerWorld?, attacker: Entity?, killed: LivingEntity?) {
        if (shouldEventBeHandled(world, attacker, killed)) {
            val soulCaptureLevel = EnchantmentHelper.getLevel(this, attacker.mainHandStack)
            if (world.random.getBooleanWithPerMilleChance(soulCaptureLevel)) {
                dropSpawnEgg(killed)
            }
        }
    }

    companion object {

        fun Random.getBooleanWithPerMilleChance(perMille: Int): Boolean {
            if (perMille < 0 || perMille > 1000) throw IllegalArgumentException()
            if (perMille == 0) return false
            return this.nextInt(1000) < perMille
        }

        fun dropSpawnEgg(killed: LivingEntity) = killed.dropItem {
            SpawnEggItem.forEntity(killed.type)
        }

        @OptIn(ExperimentalContracts::class)
        fun shouldEventBeHandled(world: ServerWorld?, attacker: Entity?, killed: LivingEntity?): Boolean {
            contract { returns(true) implies (world != null && attacker != null && killed != null && attacker is PlayerEntity) }
            return world != null && attacker != null && killed != null && attacker is PlayerEntity
        }
    }
}
