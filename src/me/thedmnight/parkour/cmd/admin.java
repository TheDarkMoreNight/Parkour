package me.thedmnight.parkour.cmd;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class admin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("admin")) {
			if (sender instanceof Player && sender.isOp()) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.BLUE + "Admin> " + ChatColor.GRAY + "/admin [sub-command]");
				} else {
					Player p = (Player) sender;
					if (args[0].equalsIgnoreCase("gm")) {
						if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
							p.setGameMode(GameMode.SURVIVAL);
							sender.sendMessage("§9Admin> §3Your gamemode has been updated.");
							p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						} else if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) {
							p.setGameMode(GameMode.CREATIVE);
							sender.sendMessage("§9Admin> §3Your gamemode has been updated.");
							p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
						}
					} else if (args[0].equalsIgnoreCase("ABCD")) {
						sender.sendMessage("I don't know about next command i'll do XD");
					} else {
						sender.sendMessage("§9Admin> §7Unknown sub-commands.");
					}
				}
			}
		}
		return true;
	}

}
