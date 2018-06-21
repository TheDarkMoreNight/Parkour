package me.thedmnight.parkour.api;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class CheckPointConf {

	static CheckPointConf instance = new CheckPointConf();
	Plugin p;
	FileConfiguration cpdata;
	File dshfile;

	public static CheckPointConf getInstance() {
		return instance;
	}

	public void setup(Plugin p) {
		if (!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
		this.dshfile = new File(p.getDataFolder(), "CheckPoint.yml");
		if (!this.dshfile.exists()) {
			try {
				this.dshfile.createNewFile();
			} catch (IOException e) {
				Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not create CheckPoint.yml!");
			}
		}
		this.cpdata = YamlConfiguration.loadConfiguration(this.dshfile);
	}

	public FileConfiguration getData() {
		return this.cpdata;
	}

	public void saveData() {
		try {
			this.cpdata.save(this.dshfile);
		} catch (IOException e) {
			Bukkit.getServer().getLogger().severe(ChatColor.RED + "Could not save CheckPoint.yml!");
		}
	}

	public void reloadData() {
		this.cpdata = YamlConfiguration.loadConfiguration(this.dshfile);
	}

	public PluginDescriptionFile getDesc() {
		return this.p.getDescription();
	}
}
