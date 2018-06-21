package me.thedmnight.parkour.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.thedmnight.parkour.parkourMain;


public class playerJoinLeft implements Listener {

	parkourMain pl;

	public playerJoinLeft(parkourMain pl) {
		this.pl = pl;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String pn = pl.getConfig().getString("Players." + p.getName() + ".music", "true");
		if (!p.hasPlayedBefore() || pn == null || pn.isEmpty()) {
			pl.getConfig().set("Players." + p.getName() + ".music", "true");
			pl.saveConfig();
			parkourMain.getMusicThread().getSongPlayer().addPlayer(p);
		}
		parkourMain.getMusicThread().getSongPlayer().addPlayer(p);
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		parkourMain.getMusicThread().getSongPlayer().removePlayer(p);
		pl.saveConfig();
	}
}
