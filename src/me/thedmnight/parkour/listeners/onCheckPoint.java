package me.thedmnight.parkour.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.thedmnight.parkour.api.CheckPointConf;
import me.thedmnight.parkour.api.Prefix;

public class onCheckPoint implements Listener {

	@EventHandler
	public void CheckPoint(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getItem().getType() == Material.BED && e.getItem().getItemMeta().isUnbreakable()) {
			if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				FileConfiguration CPdata = CheckPointConf.getInstance().getData();
				int x = CPdata.getInt("Player." + p.getName() + ".CheckPoint.X");
				int y = CPdata.getInt("Player." + p.getName() + ".CheckPoint.Y");
				int z = CPdata.getInt("Player." + p.getName() + ".CheckPoint.Z");
				String world = CPdata.getString("Player." + p.getName() + ".CheckPoint.World");
				if (x != 0 && y != 0 && z != 0) {
					p.teleport(new Location(Bukkit.getWorld(world), x, y, z));
					p.sendMessage(Prefix.parkour + "§7Teleported to §aCheckpoint§7.");
					p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1000000, 2);
					Bukkit.broadcastMessage(Prefix.sv + "§d" + p.getName() + "§5: §bTeleported to §2Checkpoint§b.");
				} else {
					p.sendMessage(Prefix.error + "§7Not found your Checkpoint.");
				}
			}
		}
	}

}
