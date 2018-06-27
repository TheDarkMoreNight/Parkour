package me.thedmnight.parkour.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.thedmnight.parkour.api.CheckPointConf;
import me.thedmnight.parkour.api.Prefix;
import me.thedmnight.parkour.api.tools.ActionBarAPI;

public class onBlock implements Runnable, Listener {

	@SuppressWarnings("unused")
	@Override
	public void run() { // Check block
		for (Player player : Bukkit.getOnlinePlayers()) {
			Location ploc = player.getLocation();
			Location ploc2 = player.getLocation();
			ploc2.setY(ploc2.getBlockY());
			ploc.setY(ploc.getBlockY() - 1);
			if (ploc.getBlock().getType() == Material.DIAMOND_BLOCK) {
				ActionBarAPI.send(player, Prefix.parkourab + "§dGiving §b§lSPEED BUFF§d!");
				player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1.5F);
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, 1, true, false), true);
			} else if (ploc.getBlock().getType() == Material.GOLD_BLOCK) {
				ActionBarAPI.send(player, Prefix.parkourab + "§dGiving §e§lJUMP BUFF§d!");
				player.playSound(player.getLocation(), Sound.BLOCK_SLIME_PLACE, 10000, 1F);
				player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 60, 1, true, false), true);
			} else if (ploc.getBlock().getType() == Material.LAPIS_BLOCK) {
				ActionBarAPI.send(player, Prefix.parkourab + "§dGiving §b§lREGENERATION BUFF§d!");
				player.playSound(player.getLocation(), Sound.BLOCK_PORTAL_AMBIENT, 10000, 1F);
				player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 2, true, false), true);
			} else if (ploc.getBlock().getType() == Material.COAL_BLOCK) {
				ActionBarAPI.send(player, Prefix.parkourab + "§dGiving §8§lBLINDNESS BUFF§d!");
				player.playSound(player.getLocation(), Sound.ENTITY_WITHER_BREAK_BLOCK, 10000, 0F);
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 0, true, false), true);
			} else if (ploc2.getBlock().getType() == Material.SOUL_SAND) {
				ActionBarAPI.send(player, Prefix.parkourab + "§dGiving §4§lSLOW BUFF§d!");
				player.playSound(player.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 10000, 1F);
				player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 50, 1, true, false), true);
			} else if (ploc.getBlock().getType() == Material.NETHERRACK) {
				ActionBarAPI.send(player, Prefix.parkourab + "§dGiving §6§lFIRE§d!");
				player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 10000, 1F);
				player.setFireTicks(60);
			} else if (ploc.getBlock().getType() == Material.EMERALD_BLOCK) {
				boolean isCP = CheckPointConf.getInstance().getData()
						.getBoolean("Player." + player.getName() + ".CheckPoint.is");
				FileConfiguration CPdata = CheckPointConf.getInstance().getData();
				int x = CPdata.getInt("Player." + player.getName() + ".CheckPoint.X");
				int y = CPdata.getInt("Player." + player.getName() + ".CheckPoint.Y");
				int z = CPdata.getInt("Player." + player.getName() + ".CheckPoint.Z");
				String world = CPdata.getString("Player." + player.getName() + ".CheckPoint.World");
				if ((x == (int) player.getLocation().getBlockX()) && (z == (int) player.getLocation().getBlockZ())) {
					ActionBarAPI.send(player, Prefix.parkourab + "§cAlready checkpoint§d!");
				} else {
					CPdata.set("Player." + player.getName() + ".CheckPoint.X", player.getLocation().getBlockX());
					CPdata.set("Player." + player.getName() + ".CheckPoint.Y", player.getLocation().getBlockY());
					CPdata.set("Player." + player.getName() + ".CheckPoint.Z", player.getLocation().getBlockZ());
					CPdata.set("Player." + player.getName() + ".CheckPoint.World", player.getWorld().getName());
					CheckPointConf.getInstance().saveData();
					player.sendMessage(Prefix.parkour + "§a§lCHECKPOINT!");
					player.sendMessage(Prefix.parkour + "§7Your Checkpoint Location is §dX:§f"
							+ player.getLocation().getBlockX() + " §6Z:§f" + player.getLocation().getBlockZ());
					ActionBarAPI.send(player, Prefix.parkourab + "§2§lCHECKPOINT§d!");
					player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10000, 0F);
				}
			}
		}
	}

}
