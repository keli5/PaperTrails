package io.github.keli5.papertrails.command

import io.github.keli5.papertrails.ParticleMode
import io.github.keli5.papertrails.constant.acceptableParticles
import io.github.keli5.papertrails.particlePosition
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandPreviewTrail : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("This command must be run by a player.")
            return true
        }
        if (args[0] == "" || args[1] == "") return false
        if (args[0] !in acceptableParticles.keys) {
            sender.sendMessage("Invalid particle type ${args[0]}. Try /listparticles.")
            return true
        }
        if ((ParticleMode from args[1]) == null) {
            sender.sendMessage("Invalid particle mode ${args[1]}. Try /listmodes.")
            return true
        }
        val particleType = acceptableParticles[args[0]]
        val particleMode = (ParticleMode from args[1])
        val player: Player = sender


        if (particleType != null && particleMode != null) {
            player.spawnParticle(particleType, particlePosition(particleMode, player.location), 3)
            return true
        }

        return false
    }
}