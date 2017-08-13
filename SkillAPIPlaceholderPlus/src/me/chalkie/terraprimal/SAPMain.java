package me.chalkie.terraprimal;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SAPMain extends JavaPlugin {

	private SkillAPIPlaceholders SkillAPIPlaceholders = new SkillAPIPlaceholders(this);
	private CmdReload CmdReload = new CmdReload(this);

	public void onEnable() {
		registerPlaceHolderAPI();
		getCommand("sapreload").setExecutor(CmdReload);
		getConfig().options().copyDefaults(true);
		saveDefaultConfig();
		reloadConfig();
	}

	public void onDisable() {
		unRegisterPlaceHolderAPI();
	}

	public void registerPlaceHolderAPI() {
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			if (Bukkit.getPluginManager().isPluginEnabled("SkillAPI")) {
				SkillAPIPlaceholders.hook();
			} else {
				throw new RuntimeException("[SAP] Could not find SkillAPI.");
			}
		} else {
			throw new RuntimeException("[SAP] Could not find PlaceholderAPI.");
		}
	}

	public void unRegisterPlaceHolderAPI() {
		if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
			if (Bukkit.getPluginManager().isPluginEnabled("SkillAPI")) {
				SkillAPIPlaceholders.unHook();
			} else {
				throw new RuntimeException("[SAP] Could not find SkillAPI.");
			}
		} else {
			throw new RuntimeException("[SAP] Could not find PlaceholderAPI.");
		}
	}
}