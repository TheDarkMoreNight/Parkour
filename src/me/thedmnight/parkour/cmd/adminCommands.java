package me.thedmnight.parkour.cmd;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import me.thedmnight.parkour.parkourMain;
import me.thedmnight.parkour.NoteBlockAPI.Song;
import me.thedmnight.parkour.api.MusicThread;
import me.thedmnight.parkour.api.Prefix;

public class adminCommands implements CommandExecutor {

	MusicThread musicThread;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		String message = "";
		for (String part : args) {
			if (message != "")
				message += " ";
			message += part;
		}

		if (cmd.getName().equalsIgnoreCase("musicadmin")) {
			if (sender.getName().equalsIgnoreCase("Bosserza")) {
				if (args.length == 0) {
					Player p = (Player) sender;
					sender.sendMessage(ChatColor.STRIKETHROUGH + "-----------------" + ChatColor.GOLD + "["
							+ ChatColor.YELLOW + "Music" + ChatColor.GOLD + "]" + ChatColor.WHITE
							+ ChatColor.STRIKETHROUGH + "-----------------");
					sender.sendMessage(ChatColor.YELLOW + "'/musicadmin random'" + ChatColor.GOLD + " Random music");
					sender.sendMessage(ChatColor.YELLOW + "'/musicadmin play [Song]'" + ChatColor.GOLD
							+ " Play [Song] music (Use _ to space)");
					sender.sendMessage(
							ChatColor.YELLOW + "'/musicadmin list'" + ChatColor.GOLD + " List of music that available");
					sender.sendMessage(
							ChatColor.YELLOW + "'/musicadmin forcemute'" + ChatColor.GOLD + " Force [player] to mute");
					sender.sendMessage(ChatColor.YELLOW + "'/musicadmin forceunmute'" + ChatColor.GOLD
							+ " Force [player] to unmute");
					sender.sendMessage(ChatColor.YELLOW + "'/musicadmin forceallmute'" + ChatColor.GOLD
							+ " Force all online player to mute");
					sender.sendMessage(ChatColor.YELLOW + "'/musicadmin forceallunmute'" + ChatColor.GOLD
							+ " Force all online player to unmute");
					p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);
				} else {
					if (args[0].equalsIgnoreCase("play")) {
						if (args.length == 1) {
							sender.sendMessage(ChatColor.BLUE + "Music> " + ChatColor.GRAY + "Type:" + ChatColor.GREEN
									+ "/musicadmin play [Song] ('_' = space)");
						} else {
							if (args.length != 1) {
								for (Player p : Bukkit.getOnlinePlayers()) {
									parkourMain.getMusicThread().getSongPlayer().removePlayer(p);
									parkourMain.getMusicThread().trySetSong(args[1]);
								}
							}
						}
					}
					if (args[0].equalsIgnoreCase("random")) {
						for (Player p : Bukkit.getOnlinePlayers()) {
							parkourMain.getMusicThread().getSongPlayer().removePlayer(p);
							parkourMain.getMusicThread().randomSong();
						}
					}
					if (args[0].equalsIgnoreCase("reload")) {
						Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("SMDMusic");
						for (Player p : Bukkit.getOnlinePlayers()) {
							p.performCommand("music m");
							parkourMain.getMusicThread().getSongPlayer().removePlayer(p);
						}
						parkourMain.getMusicThread().getSongPlayer().setPlaying(false);
						plugin.onDisable();
						Bukkit.getServer().getScheduler().cancelTasks(plugin);
						plugin.onEnable();

					}
					if (args[0].equalsIgnoreCase("test")) {
						Bukkit.getServer().getScheduler().cancelAllTasks();
					}
					if (args[0].equalsIgnoreCase("forcemute")) {
						if (args.length == 2) {
							if (Bukkit.getServer().getPlayer(args[1]) != null) {
								Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
								String targetPlayerName = targetPlayer.getName();
								targetPlayer.performCommand("music mute");
								sender.sendMessage(ChatColor.BLUE + "Music> " + ChatColor.GRAY + "You forced "
										+ ChatColor.YELLOW + targetPlayerName + ChatColor.GRAY + " to mute");
							} else {
								sender.sendMessage(ChatColor.BLUE + "Music> " + ChatColor.GRAY + "Player "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "not found.");
							}
						} else if (args.length != 2) {
							sender.sendMessage(ChatColor.BLUE + "Music> " + ChatColor.GRAY + "Type: " + ChatColor.YELLOW
									+ "/musicadmin forcemute [player]");
						}
					}
					if (args[0].equalsIgnoreCase("forceunmute")) {
						if (args.length == 2) {
							if (Bukkit.getServer().getPlayer(args[1]) != null) {
								Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
								String targetPlayerName = targetPlayer.getName();
								targetPlayer.performCommand("music unmute");
								sender.sendMessage(ChatColor.BLUE + "Music> " + ChatColor.GRAY + "You forced "
										+ ChatColor.YELLOW + targetPlayerName + ChatColor.GRAY + " to unmute");
							} else {
								sender.sendMessage(ChatColor.BLUE + "Music> " + ChatColor.GRAY + "Player "
										+ ChatColor.YELLOW + args[1] + ChatColor.GRAY + "not found.");
							}
						} else if (args.length != 2) {
							sender.sendMessage(ChatColor.BLUE + "Music> " + ChatColor.GRAY + "Type: " + ChatColor.YELLOW
									+ "/musicadmin forceunmute [player]");
						}
					}
					if (args[0].equalsIgnoreCase("forceallmute")) {
						sender.sendMessage(ChatColor.BLUE + "Music> " + ChatColor.GRAY + "You forced "
								+ ChatColor.YELLOW + "all online player" + ChatColor.GRAY + " to mute");
						for (Player p : Bukkit.getOnlinePlayers()) {
							p.performCommand("music mute");
						}
					}
					if (args[0].equalsIgnoreCase("forceallunmute")) {
						sender.sendMessage(ChatColor.BLUE + "Music> " + ChatColor.GRAY + "You forced "
								+ ChatColor.YELLOW + "all online player" + ChatColor.GRAY + " to unmute");
						for (Player p : Bukkit.getOnlinePlayers()) {
							p.performCommand("music unmute");
						}
					}
					if (args[0].equalsIgnoreCase("list")) {
						if (sender.isOp()) {
							StringBuffer buf = new StringBuffer();
							Player p = (Player) sender;
							p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 1, 1);

							buf.append(ChatColor.GOLD + "" + ChatColor.BOLD + "Loaded songs : ");

							Song[] songs = parkourMain.getMusicThread().getSongs();

							for (int i = 0; i < songs.length; i++) {

								if (i % 2 == 0) {
									buf.append(ChatColor.YELLOW);
								}

								buf.append(songs[i].getTitle());

								if (i < songs.length - 1) {
									buf.append(", ");
								}

							}

							sender.sendMessage(buf.toString());

						} else {
							sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
						}
					}
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("checkdata")) {
			if (args.length == 0) {

			} else {
				Player target = Bukkit.getPlayer(args[0]);
				if (target != null) {
					sender.sendMessage("");
					sender.sendMessage(Prefix.sv + "§dUsername: §f" + target.getDisplayName());
					sender.sendMessage(Prefix.sv + "§eUUID: §f" + target.getUniqueId());
					sender.sendMessage(Prefix.sv + "§aIP: §f" + target.getAddress());
					sender.sendMessage("");
				} else {
					sender.sendMessage(Prefix.sv + "§7" + args[0] + " is not online.");
				}
			}
		}
		return true;
	}
}
