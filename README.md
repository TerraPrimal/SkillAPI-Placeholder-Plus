# SkillAPI-Placeholder-Plus
Extra PlaceholderAPI Placeholders For SkillAPI!

This plugin was created due to a few people wanting it and the SkillAPI developer not being interested in adding to the the basic expansion.
This should be fairly future proof. I may add requested variables but prefer to keep my spaghetti code off spigot.


# New Placeholder Variables
All variables must have SAP_ in front of them.

attrpoints - The players Attribute points.

classmaxmana - The players max Mana.

lvl - The players Level.

maxlvl - The players max Level.

mananame - The players Mana name.

manaregen - The players Mana Regen.

classname - The players Class name.

attr_<attribute_name_here> - The amount of Attribute points the player has in this Attribute.

# The Config File
The config file has one section. This section is a list of what you have named your attributes. By default the standard vitality, spirit, intelligence. dexterity and strength attributes are added.


# Commands and Permissions
/sapreload - Reloads the config file.

sap.reload

# Installation and Usage
The .jar goes into your /plugins directory NOT your PlaceholderAPI directory.
The variables can be used as %SAP_<variable_name>% or {SAP_<variable_name>} if you're using this in VentureChat.
An example of this is: %SAP_classname% or %SAP_attr_spirit%
