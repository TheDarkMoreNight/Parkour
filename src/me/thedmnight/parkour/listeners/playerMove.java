package me.thedmnight.parkour.listeners;

import org.bukkit.event.EventHandler;

import org.bukkit.event.player.PlayerMoveEvent;

import me.thedmnight.parkour.parkourMain;

import org.bukkit.event.Listener;


public class playerMove implements Listener {

	public static parkourMain pl;

	@SuppressWarnings("static-access")
	public playerMove(parkourMain pl) {
		this.pl = pl;
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {

	}

}
