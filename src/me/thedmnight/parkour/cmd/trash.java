package me.thedmnight.parkour.cmd;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class trash implements Listener, CommandExecutor{

	public void TrashGUI(Player p) {
		Inventory inv = Bukkit.getServer().createInventory(null, 27, "Trash §0| §8ESC §cto confirm");

		ItemStack info = new ItemStack(Material.PAPER);
		ItemMeta infomt = info.getItemMeta();
		infomt.setDisplayName("§4§l||| §cWarning §4§l|||");
		infomt.setLore(
				Arrays.asList("§bเธ?เธฃเธธเธ“เธฒเธ•เธฃเธงเธ?เธชเธญเธ?เธ?เธญเธ?เธ?เน?เธญเธ?เธ—เธดเน?เธ?เธ—เธธเธ?เธ?เธฃเธฑเน?เธ?", "§aเธซเธฒเธ?เธซเธฅเธ?เธ—เธดเน?เธ?เธ?เธญเธ?เน?เธ?เน?เธฅเน?เธง เธ—เธฒเธ?เธ—เธตเธกเธ?เธฒเธ?เธ?เธฐเน?เธกเน?เธฃเธฑเธ?เธ?เธดเธ”เธ?เธญเธ?"));
		info.setItemMeta(infomt);

		ItemStack confirm = new ItemStack(Material.BARRIER);
		ItemMeta cmt = confirm.getItemMeta();
		cmt.setDisplayName("§a§lConfirm");
		cmt.setLore(Arrays.asList("§dOr ESC to confirm", "§bเธ?เธฃเธธเธ“เธฒเธ•เธฃเธงเธ?เธชเธญเธ?เธ?เธญเธ?เธ?เน?เธญเธ?เธ—เธดเน?เธ?เธ—เธธเธ?เธ?เธฃเธฑเน?เธ?",
				"§aเธซเธฒเธ?เธซเธฅเธ?เธ—เธดเน?เธ?เธ?เธญเธ?เน?เธ?เน?เธฅเน?เธง เธ—เธฒเธ?เธ—เธตเธกเธ?เธฒเธ?เธ?เธฐเน?เธกเน?เธฃเธฑเธ?เธ?เธดเธ”เธ?เธญเธ?"));
		confirm.setItemMeta(cmt);

		inv.setItem(22, confirm);
		inv.setItem(26, info);

		p.openInventory(inv);
	}

	@EventHandler
	public void onTrashEvent(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		if (!e.getInventory().getName().equalsIgnoreCase("Trash §0| §8ESC §cto confirm")) {
			return;
		}
		if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR
				|| !e.getCurrentItem().hasItemMeta()) {
			return;
		} else if (e.getCurrentItem().getType() == Material.PAPER
				&& e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4§l||| §cWarning §4§l|||")) {
			e.setCancelled(true);
			p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
		} else if (e.getCurrentItem().getType() == Material.BARRIER
				&& e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lConfirm")) {
			e.setCancelled(true);
			p.closeInventory();
			p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1, 1);
		}

	}

	@EventHandler
	public void onTrashClose(InventoryOpenEvent e) {
		if (e.getInventory().getName().equalsIgnoreCase("Trash §0| §8ESC §cto confirm")) {
			for (int i = 0; i < 26; i++) {
				ItemStack info = new ItemStack(Material.PAPER);
				ItemMeta infomt = info.getItemMeta();
				infomt.setDisplayName("§4§l||| §cPlease check your item! §4§l|||");
				info.setItemMeta(infomt);

				ItemStack confirm = new ItemStack(Material.BARRIER);
				ItemMeta cmt = confirm.getItemMeta();
				cmt.setDisplayName("§a§lConfirm");
				cmt.setLore(Arrays.asList("§dOr ESC to confirm"));
				confirm.setItemMeta(cmt);

				e.getInventory().setItem(i, new ItemStack(Material.AIR));
				e.getInventory().setItem(22, confirm);
				e.getInventory().setItem(26, info);
			}
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("trash")) {
			Player p = (Player) sender;
			TrashGUI(p);
		}
		return true;
	}

}
