package de.bulldog98.soulcapture

import de.bulldog98.event.api.KilledByCallback
import net.fabricmc.api.ModInitializer
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemConvertible
import net.minecraft.item.SpawnEggItem
import net.minecraft.util.ActionResult
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class SoulCaptureInitializer : ModInitializer {
    private val SOUL_CAPTURE: Enchantment = Registry.register(
        Registry.ENCHANTMENT,
        Identifier("soulcapture", "soul_capture"),
        SoulCaptureEnchantment()
    )

    private fun handleSoulCapture(killedEntity: LivingEntity, adversary: LivingEntity?): ActionResult {
        if (adversary != null && adversary is PlayerEntity) {
            val soulCaptureLevel = EnchantmentHelper.getLevel(SOUL_CAPTURE, adversary.mainHandStack)
            val randomNumber = killedEntity.entityWorld.random.nextInt(1001) - 1
            if (randomNumber < soulCaptureLevel) {
                killedEntity.dropItem(
                    ItemConvertible {
                        return@ItemConvertible SpawnEggItem.forEntity(killedEntity.type)
                    }
                )
            }
            return ActionResult.SUCCESS
        }
        return ActionResult.PASS
    }

    override fun onInitialize() {
        println("soul capture initializing")
        KilledByCallback.EVENT.register(this::handleSoulCapture)
    }
}
