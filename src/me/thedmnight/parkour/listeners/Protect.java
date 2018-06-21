package me.thedmnight.parkour.listeners;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.thedmnight.parkour.api.Prefix;


public class Protect implements Listener, CommandExecutor {

	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("login")) {
			if (args.length == 0) {
				sender.sendMessage(Prefix.login + "§7/login §e[option]");
			} else {
				if (args[0].equalsIgnoreCase("Staff")) {
					
				} else if (args[0].equalsIgnoreCase("Builder")) {
					
				} else if (args[0].equalsIgnoreCase("Admin")) {
					
				} else if (args[0].equalsIgnoreCase("Bosserza")) {
					if (sender.getName() == "Bosserza") {
						
					} else {
						sender.sendMessage(Prefix.login + "§7You are not allowed to login as §oBosserza§7.");
					}
				} else {
					sender.sendMessage(Prefix.login + "§7Not found ");
				}
			}
		}
		return true;
	}
	
	@EventHandler
	public void Break(BlockBreakEvent e) {
		Player p = e.getPlayer();
		String pname = p.getName();
		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("Parkour").getDataFolder(),
				File.separator + "PlayerData");
		File f = new File(userdata, File.separator + pname + ".yml");
		FileConfiguration pdata = YamlConfiguration.loadConfiguration(f);
		String login = pdata.getString("Login.as");
		if (login.equalsIgnoreCase("Builder") || login.equalsIgnoreCase("Staff") || login.equalsIgnoreCase("Admin")
				|| login.equalsIgnoreCase("Bosserza")) {
			e.setCancelled(false);
		} else {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void Place(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		String pname = p.getName();
		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("Parkour").getDataFolder(),
				File.separator + "PlayerData");
		File f = new File(userdata, File.separator + pname + ".yml");
		FileConfiguration pdata = YamlConfiguration.loadConfiguration(f);
		String login = pdata.getString("Login.as");
		if (login.equalsIgnoreCase("Builder") || login.equalsIgnoreCase("Staff") || login.equalsIgnoreCase("Admin")
				|| login.equalsIgnoreCase("Bosserza")) {
			e.setCancelled(false);
		} else {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void ItemFrame(HangingBreakByEntityEvent e) {
		Player p = (Player) e.getEntity();
		String pname = p.getName();
		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("Parkour").getDataFolder(),
				File.separator + "PlayerData");
		File f = new File(userdata, File.separator + pname + ".yml");
		FileConfiguration pdata = YamlConfiguration.loadConfiguration(f);
		String login = pdata.getString("Login.as");
		if (e.getEntity() instanceof ItemFrame) {
			if (login.equalsIgnoreCase("Builder") || login.equalsIgnoreCase("Staff") || login.equalsIgnoreCase("Admin")
					|| login.equalsIgnoreCase("Bosserza")) {
				e.setCancelled(false);
			} else {
				e.setCancelled(true);
			}
		}
	}

	@SuppressWarnings("unused")
	@EventHandler
	public void FallDamage(EntityDamageEvent e) {
		Player p = (Player) e.getEntity();
		String pname = p.getName();
		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("Parkour").getDataFolder(),
				File.separator + "PlayerData");
		File f = new File(userdata, File.separator + pname + ".yml");
		FileConfiguration pdata = YamlConfiguration.loadConfiguration(f);
		String login = pdata.getString("Login.as");
		if (e.getCause() == DamageCause.FALL) {
			e.setCancelled(true);
		}
	}

	@SuppressWarnings({ "deprecation", "unused" })
	@EventHandler
	public void Potion(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		String pname = p.getName();
		File userdata = new File(Bukkit.getServer().getPluginManager().getPlugin("Parkour").getDataFolder(),
				File.separator + "PlayerData");
		File f = new File(userdata, File.separator + pname + ".yml");
		FileConfiguration pdata = YamlConfiguration.loadConfiguration(f);
		String login = pdata.getString("Login.as");
		if (e.getItem().getType() == Material.LINGERING_POTION && e.getAction() == Action.RIGHT_CLICK_AIR
				|| e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			e.setCancelled(true);
			e.setUseItemInHand(Result.DENY);
			ItemStack item = new ItemStack(Material.POTION);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(Prefix.error + "§c§oYou are not allowed to throw the Lingering Potion!");
			itemmeta.setUnbreakable(true);
			item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			item.setItemMeta(itemmeta);
			p.setItemInHand(item);
		} else {
			e.setCancelled(false);
		}
		if (e.getItem().getType() == Material.FIREBALL && e.getAction() == Action.RIGHT_CLICK_AIR
				|| e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			e.setCancelled(true);
			e.setUseItemInHand(Result.DENY);
			ItemStack item = new ItemStack(Material.MONSTER_EGG);
			ItemMeta itemmeta = item.getItemMeta();
			itemmeta.setDisplayName(Prefix.error + "§c§oYou are not allowed to use Fire Charge!");
			itemmeta.setUnbreakable(true);
			item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
			item.setItemMeta(itemmeta);
			p.setItemInHand(item);
		} else {
			e.setCancelled(false);
		}
	}


}
