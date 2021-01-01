package de.bulldog98.soulcapture

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class SoulCaptureInitializer : ModInitializer {
    private val SOUL_CAPTURE = Registry.register(
        Registry.ENCHANTMENT,
        Identifier("soulcapture", "soul_capture"),
        SoulCaptureEnchantment()
    )!!

    override fun onInitialize() {
        println("soul capture initializing")
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(SOUL_CAPTURE::handleEnchantment)
    }
}
