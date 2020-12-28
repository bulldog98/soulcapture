package de.bulldog98.soulcapture

import net.minecraft.util.Identifier

fun String.toIdentifier(): Identifier =
    Identifier(this)
