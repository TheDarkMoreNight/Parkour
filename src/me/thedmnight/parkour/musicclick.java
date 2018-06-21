package me.thedmnight.parkour;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class musicclick implements Listener {

	parkourMain pl;

	public musicclick(parkourMain pl) {
		this.pl = pl;
	}

	@EventHandler
	public void onMusicInv(PlayerInteractEvent e) {
		Player p = e.getPlayer();
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
		if (pl.getConfig().getString("Players." + p.getName() + ".music").equalsIgnoreCase("false")
				&& e.getItem().getType() == Material.RECORD_3 || e.getItem().getType() == Material.GOLD_RECORD) {
			p.getInventory().setItem(8, musicum);
			parkourMain.setPlayerVolume(p, (byte) 80);
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
			if (pl.getConfig().getString("Players." + p.getName() + ".music").equalsIgnoreCase("true")
					&& e.getItem().getType() == Material.GREEN_RECORD
					|| e.getItem().getType() == Material.GOLD_RECORD) {
				p.getInventory().setItem(8, musicm);
				p.playSound(p.getLocation(), Sound.ENTITY_ITEMFRAME_ADD_ITEM, 1, 1);
				parkourMain.getMusicThread().getSongPlayer().removePlayer(p);
				pl.getConfig().set("Players." + p.getName() + ".music", "false");
				pl.saveConfig();
			}
		}
	}

}
