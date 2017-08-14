package me.chalkie.terraprimal;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.classes.RPGClass;
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

				if (identifier.startsWith("group_")) {
					if (!SkillAPI.getClasses().isEmpty()) {
						for (RPGClass group : SkillAPI.getClasses().values()) {
							String groupName = group.getName().toLowerCase();

							if (identifier.equals("group_" + groupName + "_basehealth")) {
								return String.valueOf(group.getBaseHealth());
							}
							if (identifier.equals("group_" + groupName + "_sbasehealth")) {
								return String.valueOf((int) group.getBaseHealth());
							}
							if (identifier.equals("group_" + groupName + "_basemana")) {
								return String.valueOf(group.getBaseMana());
							}
							if (identifier.equals("group_" + groupName + "_sbasemana")) {
								return String.valueOf((int) group.getBaseMana());
							}
							if (identifier.startsWith("group_" + groupName + "_healthat:")) {
								String[] idSplit = identifier.split(":");
								try {
									int lvl = Integer.parseInt(idSplit[1]);
									return String.valueOf(group.getHealth(lvl));
								} catch (NumberFormatException e) {
									return "0";
								}
							}
							if (identifier.startsWith("group_" + groupName + "_shealthat:")) {
								String[] idSplit = identifier.split(":");
								try {
									int lvl = Integer.parseInt(idSplit[1]);
									return String.valueOf((int) group.getHealth(lvl));
								} catch (NumberFormatException e) {
									return "0";
								}
							}
							if (identifier.equals("group_" + groupName + "_healthscale")) {
								return String.valueOf(group.getHealthScale());
							}
							if (identifier.startsWith("group_" + groupName + "_manaat:")) {
								String[] idSplit = identifier.split(":");
								try {
									int lvl = Integer.parseInt(idSplit[1]);
									return String.valueOf(group.getMana(lvl));
								} catch (NumberFormatException e) {
									return "0";
								}
							}
							if (identifier.startsWith("group_" + groupName + "_smanaat:")) {
								String[] idSplit = identifier.split(":");
								try {
									int lvl = Integer.parseInt(idSplit[1]);
									return String.valueOf((int) group.getMana(lvl));
								} catch (NumberFormatException e) {
									return "0";
								}
							}
							if (identifier.equals("group_" + groupName + "_mananame")) {
								return String.valueOf(group.getManaName());
							}
							if (identifier.equals("group_" + groupName + "_smananame")) {
								return String.valueOf(ChatColor.stripColor(group.getManaName()));
							}
							if (identifier.equals("group_" + groupName + "_manaregen")) {
								return String.valueOf(group.getManaRegen());
							}
							if (identifier.equals("group_" + groupName + "_manascale")) {
								return String.valueOf(group.getManaScale());
							}
							if (identifier.equals("group_" + groupName + "_maxlevel")) {
								return String.valueOf(group.getMaxLevel());
							}
							if (identifier.equals("group_" + groupName + "_parent")) {
								if (group.getParent() != null) {
									return String.valueOf(group.getParent().getName());
								} else {
									return "0";
								}
							}
							if (identifier.equals("group_" + groupName + "_prefix")) {
								return String.valueOf(group.getPrefix());
							}
							if (identifier.equals("group_" + groupName + "_sprefix")) {
								return String.valueOf(ChatColor.stripColor(group.getPrefix()));
							}
							if (identifier.startsWith("group_" + groupName + "_reqexpat:")) {
								String[] idSplit = identifier.split(":");
								try {
									int lvl = Integer.parseInt(idSplit[1]);
									return String.valueOf(group.getRequiredExp(lvl));
								} catch (NumberFormatException e) {
									return "0";
								}
							}
						}
					}
				}

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

				if (identifier.startsWith("default_")) {
					if (identifier.equals("default_currentlevel")) {
						return String.valueOf(data.getMainClass().getLevel());
					}
					if (identifier.equals("default_currentmaxlevel")) {
						return String.valueOf(data.getMainClass().getData().getMaxLevel());
					}
					if (identifier.equals("default_currentmaxmana")) {
						return String.valueOf(data.getMainClass().getPlayerData().getMaxMana());
					}
					if (identifier.equals("default_scurrentmaxmana")) {
						return String.valueOf((int) data.getMainClass().getPlayerData().getMaxMana());
					}
					if (identifier.equals("default_currentmana")) {
						return String.valueOf(data.getMainClass().getPlayerData().getMana());
					}
					if (identifier.equals("default_scurrentmana")) {
						return String.valueOf((int) data.getMainClass().getPlayerData().getMana());
					}
					if (identifier.equals("default_currentmaxhealth")) {
						double maxHP = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
						return String.valueOf(maxHP);
					}
					if (identifier.equals("default_scurrentmaxhealth")) {
						double maxHP = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
						return String.valueOf((int) maxHP);
					}
					if (identifier.equals("default_currenthealth")) {
						double currentHP = player.getHealth();
						return String.valueOf(currentHP);
					}
					if (identifier.equals("default_scurrenthealth")) {
						double currentHP = player.getHealth();
						return String.valueOf((int) currentHP);
					}
					if (identifier.equals("default_currentmananame")) {
						return String.valueOf(data.getMainClass().getData().getManaName());
					}
					if (identifier.equals("default_scurrentmananame")) {
						return String.valueOf(ChatColor.stripColor(data.getMainClass().getData().getManaName()));
					}
					if (identifier.equals("default_currentmanaregen")) {
						return String.valueOf(data.getMainClass().getData().getManaRegen());
					}
					if (identifier.equals("default_scurrentmanaregen")) {
						return String.valueOf((int) data.getMainClass().getData().getManaRegen());
					}
					if (identifier.equals("default_currentgroupname")) {
						return String.valueOf(data.getMainClass().getData().getName());
					}
					if (identifier.equals("default_currentavailableattributepoints")) {
						return String.valueOf(data.getMainClass().getPlayerData().getAttributePoints());
					}
					if (identifier.equals("default_currentprefix")) {
						return String.valueOf(data.getMainClass().getData().getPrefix());
					}
					if (identifier.equals("default_scurrentprefix")) {
						return String.valueOf(ChatColor.stripColor(data.getMainClass().getData().getPrefix()));
					}
					if (identifier.equals("default_currentexp")) {
						return String.valueOf(data.getMainClass().getExp());
					}
					if (identifier.equals("default_scurrentexp")) {
						return String.valueOf((int) data.getMainClass().getExp());
					}
					if (identifier.equals("default_currentrequiredexp")) {
						return String.valueOf(data.getMainClass().getRequiredExp());
					}
					if (identifier.equals("default_scurrentrequiredexp")) {
						return String.valueOf(data.getMainClass().getRequiredExp());
					}
				}

				if (identifier.startsWith("player_")) {
					if (!data.getClasses().isEmpty()) {
						for (PlayerClass group : data.getClasses()) {
							String groupName = group.getData().getName().toLowerCase();

							if (identifier.startsWith("player_" + groupName + "_attribute:")) {
								String[] idSplit = identifier.split(":");
								try {
									return String.valueOf(group.getPlayerData().getAttribute(idSplit[1]));
								} catch (Exception e) {
									return "0";
								}
							}
							if (identifier.equals("player_" + groupName + "_availableattributepoints")) {
								return String.valueOf(group.getPlayerData().getAttributePoints());
							}
							if (identifier.startsWith("player_" + groupName + "_investedattributepoints:")) {
								String[] idSplit = identifier.split(":");
								try {
									return String.valueOf(group.getPlayerData().getInvestedAttribute(idSplit[1]));
								} catch (Exception e) {
									return "0";
								}
							}
							if (identifier.equals("player_" + groupName + "_mainclass")) {
								return String.valueOf(group.getPlayerData().getMainClass().getData().getName());
							}

							if (identifier.equals("player_" + groupName + "_currentexp")) {
								return String.valueOf(group.getExp());
							}
							if (identifier.equals("player_" + groupName + "_requiredexp")) {
								return String.valueOf(group.getRequiredExp());
							}
							if (identifier.equals("player_" + groupName + "_scurrentexp")) {
								return String.valueOf((int) group.getExp());
							}
							if (identifier.equals("player_" + groupName + "_srequiredexp")) {
								return String.valueOf((int) group.getRequiredExp());
							}
							if (identifier.equals("player_" + groupName + "_level")) {
								return String.valueOf(group.getLevel());
							}
							if (identifier.equals("player_" + groupName + "_currentmana")) {
								return String.valueOf(group.getPlayerData().getMana());
							}
							if (identifier.equals("player_" + groupName + "_maxmana")) {
								return String.valueOf(group.getPlayerData().getMaxMana());
							}
							if (identifier.equals("player_" + groupName + "_scurrentmana")) {
								return String.valueOf((int) group.getPlayerData().getMana());
							}
							if (identifier.equals("player_" + groupName + "_smaxmana")) {
								return String.valueOf((int) group.getPlayerData().getMaxMana());
							}
							if (identifier.equals("player_" + groupName + "_scurrenthealth")) {
								double currentHP = player.getHealth();
								return String.valueOf((int) currentHP);
							}
							if (identifier.equals("player_" + groupName + "_smaxhealth")) {
								double maxHP = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue();
								return String.valueOf((int) maxHP);
							}
							if (identifier.startsWith("player_" + groupName + "_skillevel:")) {
								String[] idSplit = identifier.split(":");
								try {
									return String.valueOf(group.getPlayerData().getSkillLevel(idSplit[1]));
								} catch (Exception e) {
									return "0";
								}
							}
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