package de.raspifreak.banhammer.listener;

import org.bukkit.BanList.Type;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

import de.raspifreak.banhammer.BanHammer;

public class BanHammerListener implements Listener {

	private BanHammer plugin;

	public BanHammerListener(BanHammer plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onClickEvent(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Player) {
			Player p = (Player) event.getDamager();
			if (p.getItemInHand().getItemMeta().getDisplayName()
					.equals(plugin.getConfig().getString("ban.itemname").replace('&', '§'))) {
				if (p.isOp() || p.hasPermission("banhammer.ban.use")) {
					if (event.getEntity() instanceof Player) {
						Player pt = (Player) event.getEntity();
						plugin.getServer().getBanList(Type.NAME).addBan(pt.getName(),
								plugin.getConfig().getString("ban.message.banned"), null, "");
						pt.kickPlayer(plugin.getConfig().getString("ban.message.banned"));
						p.sendMessage(BanHammer.mod_prefix + plugin.getConfig().getString("ban.message.use"));
					} else {
						p.sendMessage(BanHammer.mod_prefix + "Das ist kein Spieler!");
					}
					event.setCancelled(true);
				} else {
					p.sendMessage(plugin.getConfig().getString("ban.message.dontuse"));
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onDropItem(PlayerDropItemEvent event) {
		if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName()
				.equals(plugin.getConfig().getString("ban.itemname").replace('&', '§'))) {
			event.getItemDrop().remove();
		}
	}
}
