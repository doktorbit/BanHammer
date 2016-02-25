package de.raspifreak.banhammer;

import org.bukkit.plugin.java.JavaPlugin;

import de.raspifreak.banhammer.commands.GiveBanHammerCommand;
import de.raspifreak.banhammer.listener.BanHammerListener;

public class BanHammer extends JavaPlugin {

	public static String mod_name = "";
	public static String mod_version = "";
	public static String mod_prefix = "";

	@Override
	public void onEnable() {
		mod_name = this.getDescription().getName();
		mod_version = this.getDescription().getVersion();
		mod_prefix = "[" + mod_name + "] ";
		getConfig().set("ban.message.banned", "&cThe BanHammer has spoken!");
		getConfig().set("ban.message.use", "&6Der Spieler wurde gebannt!");
		getConfig().set("ban.message.give", "&6Hier ist dein Spielzeug!");
		getConfig().set("ban.message.dontuse", "&cEy! Du darfst das nicht! >:(");
		getConfig().set("ban.itemname", "&cBanHammer");
		getConfig().options().copyDefaults(true);
		saveConfig();

		new BanHammerListener(this);
		new GiveBanHammerCommand(this);
		
		getServer().getConsoleSender().sendMessage("Plugin wurde aktiviert!");
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage("Plugin wurde deaktiviert!");
	}
}
