package de.raspifreak.banhammer.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.raspifreak.banhammer.BanHammer;

public class GiveBanHammerCommand implements CommandExecutor {

	private BanHammer plugin;

	public GiveBanHammerCommand(BanHammer plugin) {
		this.plugin = plugin;
		plugin.getCommand("banhammer").setExecutor(this);
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.isOp() || p.hasPermission("banhammer.ban.use")) {
				if (args.length == 0) {
					ItemStack bItem = new ItemStack(Material.DIAMOND_SWORD);
					ItemMeta im = bItem.getItemMeta();
					im.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ban.itemname")));
					bItem.setItemMeta(im);
					p.getInventory().addItem(bItem);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ban.message.give")));
				}
			}
		}
		return true;
	}

}
