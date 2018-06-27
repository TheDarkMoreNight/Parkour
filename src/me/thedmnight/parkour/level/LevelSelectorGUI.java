package me.thedmnight.parkour.level;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class LevelSelectorGUI {

	private Inventory inv;
	private ItemStack l1, l2, l3, l4, l5, l6, l7, l8, l9, l10;
	
	public LevelSelectorGUI(Player p) {
		
		inv = Bukkit.createInventory(null, 36, "");
		
		
		
	}
	
	public ItemStack createItem(Material mat, int level, String dif) {
		ItemStack item = new ItemStack(mat);
		ItemMeta itemmeta = item.getItemMeta();
		itemmeta.setDisplayName("§6§lLevel§6: §b§l" + String.valueOf(level));
		itemmeta.setLore(Arrays.asList(ChatColor.DARK_AQUA + "Difficulty: " + ChatColor.RESET + dif));
		return item;
	}

}
