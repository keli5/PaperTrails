package io.github.keli5.papertrails.command

import io.github.keli5.papertrails.constant.ParticleMode
import io.github.keli5.papertrails.constant.acceptableParticles
import io.github.keli5.papertrails.constant.particlePosition
import org.bukkit.Particle
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandPreviewTrail : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) {
            sender.sendMessage("This command must be run by a player.")
            return false
        }
        val player: Player = sender
        val particleType: Particle? = acceptableParticles[args?.getOrNull(0)]
        val particleMode: ParticleMode? = ParticleMode from args?.getOrNull(1)

        return if (particleType == null) {
            player.sendMessage("Invalid particle type. Try /listparticles.")
            false
        } else if (particleMode == null) {
            player.sendMessage("Invalid particle mode. Try /listmodes.")
            false
        } else {
            player.spawnParticle(particleType, particlePosition(particleMode, player.location), 3)
            true
        }

    }
}