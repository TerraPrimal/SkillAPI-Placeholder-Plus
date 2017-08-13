package me.chalkie.terraprimal;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdReload implements CommandExecutor {
	public static SAPMain plugin;

	public CmdReload(SAPMain instance) {
		plugin = instance;
	}

	public boolean onCommand(final CommandSender sender, Command command, String commandLabel, String[] args) {
		if (command.getName().equalsIgnoreCase("sapreload")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				player.sendMessage("SkillAPI Placeholder Plus Reloaded.");
				reloadConfig();
				return true;
			} else {
				System.out.print("SkillAPI Placeholder Plus Reloaded.");
				reloadConfig();
				return true;
			}
		}
		return false;
	}

	public void reloadConfig() {
		plugin.reloadConfig();
		plugin.saveConfig();
		plugin.getConfig();
	}
}