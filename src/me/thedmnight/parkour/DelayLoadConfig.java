package me.thedmnight.parkour;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.thedmnight.parkour.listeners.playerMove;
public class DelayLoadConfig implements Runnable {

	private boolean isRunning = true;

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	@Override
	public void run() {

		while (isRunning == true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			for (Player p : Bukkit.getOnlinePlayers()) {

				if (playerMove.pl.getConfig().getString("Players." + p.getName() + ".music") == "true") {
					parkourMain.getMusicThread().getSongPlayer().addPlayer(p);
				} else {
					parkourMain.getMusicThread().getSongPlayer().removePlayer(p);
				}

			}

		}

	}

}
