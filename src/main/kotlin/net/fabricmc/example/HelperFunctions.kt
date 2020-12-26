package net.fabricmc.example

import net.minecraft.util.Identifier

fun String.toIdentifier(): Identifier =
    Identifier(this)
