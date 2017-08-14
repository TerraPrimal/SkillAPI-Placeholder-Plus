package me.chalkie.terraprimal;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.player.PlayerClass;
import com.sucy.skill.api.player.PlayerData;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.PlaceholderHook;

public class SkillAPIPlaceholders implements Listener {
	public static SAPMain plugin;

	public SkillAPIPlaceholders(SAPMain instance) {
		plugin = instance;
	}

	public void hook() {
		boolean hooked = PlaceholderAPI.registerPlaceholderHook("SAP", new PlaceholderHook() {
			@Override
			public String onPlaceholderRequest(Player player, String identifier) {
				if (player == null) {
					return "0";
				}

				if (!SkillAPI.hasPlayerData(player)) {
					return "0";
				}

				PlayerData data = SkillAPI.getPlayerData(player);

				if (data == null) {
					return "0";
				}

				if (identifier.equals("attrpoints")) {
					return String.valueOf(data.getAttributePoints());
				}

				if (identifier.equals("classmaxmana")) {
					return String.valueOf(data.getMaxMana());
				}

				if (identifier.equals("lvl")) {
					return String.valueOf(data.getMainClass().getLevel());
				}

				if (identifier.equals("maxlvl")) {
					return String.valueOf(data.getMainClass().getData().getMaxLevel());
				}

				if (identifier.equals("mananame")) {
					return String.valueOf(data.getMainClass().getData().getManaName());
				}

				if (identifier.equals("manaregen")) {
					return String.valueOf(data.getMainClass().getData().getManaRegen());
				}

				if (identifier.equals("classname")) {
					return String.valueOf(data.getMainClass().getData().getName());
				}

				if (identifier.startsWith("group_"))
				{
					if (!data.getClasses().isEmpty())
					{
						for (PlayerClass group : data.getClasses())
						{
							String className = group.getData().getName().toLowerCase();

							if (identifier.equals("group_" + className + "_expreq"))
							{
								return String.valueOf(group.getRequiredExp());
							}
							if (identifier.equals("group_" + className + "_exptotal"))
							{
								return String.valueOf(group.getTotalExp());
							}
							if (identifier.equals("group_" + className + "_exp"))
							{
								return String.valueOf(group.getExp());
							}
						}
					}
					return "0";
				}

				if (plugin.getConfig().contains("Attributes"))
				{
					List<String> attrList = plugin.getConfig().getStringList("Attributes");

					for (String attr : attrList) {
						if (identifier.equals("attr_" + attr)) {
							return String.valueOf(data.getAttribute(attr));
						}
					}
				}
				return "0";
			}
		});

		if (hooked) {
			System.out.print("[SAP] SkillAPI Placeholders Plus has been registered.");
		}
	}

	public void unHook() {
		boolean unHooked = PlaceholderAPI.unregisterPlaceholderHook("SAP");

		if (unHooked) {
			System.out.print("[SAP] SkillAPI Placeholders Plus has been unregistered.");
		}
	}

}