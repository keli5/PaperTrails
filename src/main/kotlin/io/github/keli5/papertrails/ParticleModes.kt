package io.github.keli5.papertrails

import org.bukkit.Location
import org.bukkit.World

fun aboveHead(world: World, x: Double, y: Double, z: Double): Location {
    return Location(world, x, y+2, z)
}

enum class ParticleMode(val modeName: String) {
    ABOVEHEAD("abovehead");

    companion object {
        infix fun from(value: String): ParticleMode? = ParticleMode.values().firstOrNull { it.modeName == value }
    }
}

val ParticleModes = mapOf(
    ParticleMode.ABOVEHEAD to ::aboveHead
)

fun particlePosition(mode: ParticleMode, world: World, x: Double, y: Double, z: Double): Location {
    return ParticleModes[mode]!!(world, x, y, z)
}

fun particlePosition(mode: ParticleMode, location: Location): Location {
    return ParticleModes[mode]!!(location.world, location.x, location.y, location.z)
}

fun particleMode(modeName: String): ParticleMode {
    var mode = ParticleMode from modeName
    if (mode == null) mode = ParticleMode.ABOVEHEAD
    return mode
}