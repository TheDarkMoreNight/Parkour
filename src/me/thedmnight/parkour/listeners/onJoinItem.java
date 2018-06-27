package me.thedmnight.parkour.listeners;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import me.thedmnight.parkour.parkourMain;
import me.thedmnight.parkour.api.Prefix;


public class onJoinItem implements Listener {

	parkourMain pl;

	public onJoinItem(parkourMain pl) {
		this.pl = pl;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.getInventory().clear();
		//
		String title = parkourMain.getMusicThread().getCurrentSong().getTitle();
		String author = parkourMain.getMusicThread().getCurrentSong().getAuthor();
		ItemStack musicm = new ItemStack(Material.RECORD_3, 1);
		ItemMeta musicmt = musicm.getItemMeta();
		musicmt.setDisplayName("§f§lMusic Station: §cOff");
		musicmt.setLore(Arrays.asList("§d§lSong Info", "§6§lSong: §e" + title, "§2§lAuthor: §a" + author));
		musicm.setItemMeta(musicmt);
		ItemStack musicum = new ItemStack(Material.GREEN_RECORD, 1);
		ItemMeta musicumt = musicum.getItemMeta();
		musicumt.setDisplayName("§f§lMusic Station: §aOn");
		musicumt.setLore(Arrays.asList("§d§lSong Info", "§6§lSong: §e" + title, "§2§lAuthor: §a" + author));
		musicum.setItemMeta(musicumt);
		if (pl.getConfig().getString("Players." + p.getName() + ".music").equalsIgnoreCase("false")) {
			p.getInventory().setItem(8, musicum);
			parkourMain.setPlayerVolume(p, (byte) 90);
			parkourMain.getPlayerVolume(p);
			parkourMain.getMusicThread().getCurrentSong().getSpeed();
			parkourMain.getMusicThread().getCurrentSong().getLength();
			if (title.isEmpty()) {
				title = "§8?§r";
			}
			if (author.isEmpty()) {
				author = "§8?§r";
			}
			p.playSound(p.getLocation(), Sound.ENTITY_ITEMFRAME_REMOVE_ITEM, 1, 1);
			// p.playSound(p.getLocation(), Sound.<SOUND>,
			// <VOLUME>,
			// <PITCH>);
			parkourMain.getMusicThread().getSongPlayer().addPlayer(p);
			pl.getConfig().set("Players." + p.getName() + ".music", "true");
			pl.saveConfig();
		} else {
			if (pl.getConfig().getString("Players." + p.getName() + ".music").equalsIgnoreCase("true")) {
				p.getInventory().setItem(8, musicm);
				p.playSound(p.getLocation(), Sound.ENTITY_ITEMFRAME_ADD_ITEM, 1, 1);
				parkourMain.getMusicThread().getSongPlayer().removePlayer(p);
				pl.getConfig().set("Players." + p.getName() + ".music", "false");
				pl.saveConfig();
			}
		}
		//
		ItemStack cp = new ItemStack(Material.BED);
		ItemMeta cp1 = cp.getItemMeta();
		cp1.setDisplayName("§eBack to §a§lCHECKPOINT");
		cp1.setUnbreakable(true);
		cp.setItemMeta(cp1);
		p.getInventory().setItem(3, cp);
		//
	}

	@EventHandler
	public void onInventory(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		if (e.getClickedInventory().getName() == p.getName()) {
			if (e.getCurrentItem().getType() == Material.GREEN_RECORD
					|| e.getCurrentItem().getType() == Material.RECORD_3
					|| e.getCurrentItem().getType() == Material.BED && e.getCurrentItem().hasItemMeta()) {
				e.setCancelled(true);
			} else {
				return;
			}
		} else {
			return;
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		e.setCancelled(true);
		p.sendMessage(Prefix.sv + "§7You can't drop item in this server.");
	}
}
