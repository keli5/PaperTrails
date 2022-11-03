package io.github.keli5.papertrails.constant

import org.bukkit.Location
import org.bukkit.World
import java.util.Calendar
import kotlin.math.sin

fun aboveHead(world: World, x: Double, y: Double, z: Double): Location {
    return Location(world, x, y+2, z)
}
fun aboveHeadBounce(world: World, x: Double, y: Double, z: Double): Location {
    return Location(world, x,
                 y + (sin(Calendar.getInstance().timeInMillis.toDouble() / 1000) * 0.5) + 0.5 + 2,
                    z)  // time divided by 1000 for more continuous sin output
}


enum class ParticleMode(val modeName: String) {
    ABOVEHEAD("abovehead"),
    ABOVEHEAD_BOUNCE("aboveheadbounce");

    companion object {
        infix fun from(value: String?): ParticleMode? = ParticleMode.values().firstOrNull { it.modeName == value }
    }
}

val ParticleModes = mapOf(
    ParticleMode.ABOVEHEAD to ::aboveHead,
    ParticleMode.ABOVEHEAD_BOUNCE to ::aboveHeadBounce
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