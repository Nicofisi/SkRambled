package com.gmail.headshot;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.conditions.base.PropertyCondition;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

import com.gmail.headshot.conditions.mcMMO.CondIsUsingAdminChat;
import com.gmail.headshot.conditions.mcMMO.CondIsUsingPartyChat;
import com.gmail.headshot.effects.factions.EffClaimLand;
import com.gmail.headshot.effects.factions.EffCreateFaction;
import com.gmail.headshot.effects.factions.EffDisbandFaction;
import com.gmail.headshot.effects.factions.EffInvitePlayer;
import com.gmail.headshot.effects.factions.EffKickPlayer;
import com.gmail.headshot.effects.factions.EffUnClaimLand;
import com.gmail.headshot.effects.mcMMO.EffSendAdminMesssage;
import com.gmail.headshot.effects.mcMMO.EffSendPartyMessage;
import com.gmail.headshot.events.factions.EvtFactionCreateEvent;
import com.gmail.headshot.events.factions.EvtFactionDescriptionChangeEvent;
import com.gmail.headshot.events.factions.EvtFactionDisbandEvent;
import com.gmail.headshot.events.factions.EvtFactionNameChangeEvent;
import com.gmail.headshot.events.general.EvtCropGrowEvent;
import com.gmail.headshot.events.general.EvtHeadRotateEvent;
import com.gmail.headshot.events.general.EvtRepairEvent;
import com.gmail.headshot.events.general.EvtTeleportCallEvent;
import com.gmail.headshot.events.worldguard.EvtRegionEnterEvent;
import com.gmail.headshot.expressions.factions.ExprFactionAllyList;
import com.gmail.headshot.expressions.factions.ExprFactionClaim;
import com.gmail.headshot.expressions.factions.ExprFactionDescription;
import com.gmail.headshot.expressions.factions.ExprFactionEnemyList;
import com.gmail.headshot.expressions.factions.ExprFactionHome;
import com.gmail.headshot.expressions.factions.ExprFactionList;
import com.gmail.headshot.expressions.factions.ExprFactionMOTD;
import com.gmail.headshot.expressions.factions.ExprFactionName;
import com.gmail.headshot.expressions.factions.ExprPlayerPower;
import com.gmail.headshot.expressions.factions.ExprPlayerPowerBoost;
import com.gmail.headshot.expressions.factions.ExprFactionTitle;
import com.gmail.headshot.expressions.factions.ExprFactionTruceList;
import com.gmail.headshot.expressions.factions.ExprPlayerFaction;
import com.gmail.headshot.expressions.factions.ExprPlayerList;
import com.gmail.headshot.expressions.factions.ExprRankOfPlayer;
import com.gmail.headshot.expressions.factions.ExprRelationShipStatus;
import com.gmail.headshot.expressions.factions.ExprSetFactionPower;
import com.gmail.headshot.expressions.factions.ExprSetFactionPowerBoost;
import com.gmail.headshot.expressions.general.ExprAmountOfVars;
import com.gmail.headshot.expressions.general.ExprPitchOfPlayer;
import com.gmail.headshot.expressions.general.ExprYawOfPlayer;
import com.gmail.headshot.expressions.mcMMO.ExprLevel;
import com.gmail.headshot.expressions.mcMMO.ExprPowerLevel;
import com.gmail.headshot.expressions.worldguard.ExprFlagOfRegion;
import com.gmail.headshot.expressions.worldguard.ExprFlagsOfRegion;
import com.gmail.headshot.expressions.worldguard.ExprMembersOfRegion;
import com.gmail.headshot.expressions.worldguard.ExprOwnersOfRegion;
import com.gmail.headshot.expressions.worldguard.ExprRegionAtLocation;
import com.gmail.nossr50.datatypes.party.Party;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.party.PartyManager;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.protection.flags.DefaultFlag;
import com.sk89q.worldguard.protection.flags.Flag;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class Register {

	public static void registerSkript() {

		Skript.registerAddon(SKRambled.getInstance());

		Skript.registerEvent("Head Rotate", SimpleEvent.class,
				EvtHeadRotateEvent.class,
				new String[] { "head (rotat(e|ion)|move[ment])" });

		EventValues.registerEventValue(EvtHeadRotateEvent.class, Player.class,
				new Getter<Player, EvtHeadRotateEvent>() {
					@Override
					public Player get(EvtHeadRotateEvent event) {
						return event.getPlayer();
					}
				}, 0);
		Skript.registerEvent("Repair", SimpleEvent.class, EvtRepairEvent.class,
				new String[] { "repair" });

		EventValues.registerEventValue(EvtRepairEvent.class, Player.class,
				new Getter<Player, EvtRepairEvent>() {
					@Override
					public Player get(EvtRepairEvent event) {
						return event.getPlayer();
					}
				}, 0);

		EventValues.registerEventValue(EvtRepairEvent.class, ItemStack.class,
				new Getter<ItemStack, EvtRepairEvent>() {
					@Override
					public ItemStack get(EvtRepairEvent event) {
						return event.getItem();
					}
				}, 0);
		Skript.registerEvent("Teleport Call", SimpleEvent.class,
				EvtTeleportCallEvent.class,
				new String[] { "[player] teleport call" });

		EventValues.registerEventValue(EvtTeleportCallEvent.class,
				Player.class, new Getter<Player, EvtTeleportCallEvent>() {
					@Override
					public Player get(EvtTeleportCallEvent event) {
						return event.getPlayer();
					}
				}, 0);
		Skript.registerEvent("Crop Grow", SimpleEvent.class,
				EvtCropGrowEvent.class,
				new String[] { "[skrambled] crop grow" });

		EventValues.registerEventValue(EvtCropGrowEvent.class, Block.class,
				new Getter<Block, EvtCropGrowEvent>() {
					@Override
					public Block get(EvtCropGrowEvent event) {
						return event.getBlock();
					}
				}, 0);
		Skript.registerExpression(ExprPitchOfPlayer.class, Float.class,
				ExpressionType.SIMPLE, "pitch of %player%", "%player%'s pitch");
		Skript.registerExpression(ExprYawOfPlayer.class, Float.class,
				ExpressionType.SIMPLE, "yaw of %player%", "%player%'s yaw");
		Skript.registerExpression(ExprAmountOfVars.class, Number.class,
				ExpressionType.SIMPLE, "(size|amount) of [all] variables");
	}

	public static void registerAllFactions() {
		Skript.registerEvent("Faction Description Change", SimpleEvent.class,
				EvtFactionDescriptionChangeEvent.class,
				new String[] { "faction description change" });

		EventValues.registerEventValue(EvtFactionDescriptionChangeEvent.class,
				Player.class,
				new Getter<Player, EvtFactionDescriptionChangeEvent>() {
					@Override
					public Player get(
							EvtFactionDescriptionChangeEvent evtFactionDescriptionChangeEvent) {
						return evtFactionDescriptionChangeEvent.getPlayer();
					}
				}, 0);

		EventValues.registerEventValue(EvtFactionDescriptionChangeEvent.class,
				Faction.class,
				new Getter<Faction, EvtFactionDescriptionChangeEvent>() {
					@Override
					public Faction get(
							EvtFactionDescriptionChangeEvent evtFactionDescriptionChangeEvent) {
						return evtFactionDescriptionChangeEvent.getFac();
					}
				}, 0);
		EventValues.registerEventValue(EvtFactionDescriptionChangeEvent.class,
				String.class,
				new Getter<String, EvtFactionDescriptionChangeEvent>() {
					@Override
					public String get(
							EvtFactionDescriptionChangeEvent evtFactionDescriptionChangeEvent) {
						return evtFactionDescriptionChangeEvent.getNewDesc();
					}
				}, 0);
		Skript.registerEvent("Faction Name Change", SimpleEvent.class,
				EvtFactionNameChangeEvent.class,
				new String[] { "faction name change" });

		EventValues.registerEventValue(EvtFactionNameChangeEvent.class,
				Player.class, new Getter<Player, EvtFactionNameChangeEvent>() {
					@Override
					public Player get(
							EvtFactionNameChangeEvent evtFactionNameChangeEvent) {
						return evtFactionNameChangeEvent.getPlayer();
					}
				}, 0);

		EventValues.registerEventValue(EvtFactionNameChangeEvent.class,
				Faction.class,
				new Getter<Faction, EvtFactionNameChangeEvent>() {
					@Override
					public Faction get(
							EvtFactionNameChangeEvent evtFactionNameChangeEvent) {
						return evtFactionNameChangeEvent.getFac();
					}
				}, 0);
		Skript.registerEvent("Faction Create", SimpleEvent.class,
				EvtFactionCreateEvent.class, new String[] { "faction create" });

		EventValues.registerEventValue(EvtFactionCreateEvent.class,
				Player.class, new Getter<Player, EvtFactionCreateEvent>() {
					@Override
					public Player get(
							EvtFactionCreateEvent evtFactionCreateEvent) {
						return evtFactionCreateEvent.getPlayer();
					}
				}, 0);

		EventValues.registerEventValue(EvtFactionCreateEvent.class,
				String.class, new Getter<String, EvtFactionCreateEvent>() {
					@Override
					public String get(
							EvtFactionCreateEvent evtFactionCreateEvent) {
						return evtFactionCreateEvent.getFacName();
					}
				}, 0);
		Skript.registerEvent("Faction Disband", SimpleEvent.class,
				EvtFactionDisbandEvent.class,
				new String[] { "faction disband" });
		EventValues.registerEventValue(EvtFactionDisbandEvent.class,
				Player.class, new Getter<Player, EvtFactionDisbandEvent>() {
					@Override
					public Player get(
							EvtFactionDisbandEvent evtFactionDisbandEvent) {
						return evtFactionDisbandEvent.getPlayer();
					}
				}, 0);

		EventValues.registerEventValue(EvtFactionDisbandEvent.class,
				String.class, new Getter<String, EvtFactionDisbandEvent>() {
					@Override
					public String get(
							EvtFactionDisbandEvent evtFactionDisbandEvent) {
						return evtFactionDisbandEvent.getFacName();
					}
				}, 0);

		Skript.registerEffect(EffUnClaimLand.class,
				"unclaim land at %location%");
		Skript.registerEffect(EffClaimLand.class,
				"claim land for [the faction] %faction% at %location%");
		Skript.registerEffect(EffInvitePlayer.class,
				"invite %player% to [the faction] %faction%");
		Skript.registerEffect(EffKickPlayer.class,
				"[skrambled] remove %player% from [the faction] %faction%");
		Skript.registerEffect(EffDisbandFaction.class,
				"[skrambled] disband [the faction] %faction%");
		Skript.registerEffect(EffCreateFaction.class,
				"[skrambled] create a faction [with name] %string% with leader %player%");
		SimplePropertyExpression.register(ExprFactionName.class, String.class,
				"name", "faction");
		SimplePropertyExpression.register(ExprPlayerFaction.class,
				Faction.class, "faction", "player");
		SimplePropertyExpression.register(ExprFactionDescription.class,
				String.class, "description", "faction");
		SimplePropertyExpression.register(ExprPlayerPower.class, Double.class,
				"power", "player");
		SimplePropertyExpression.register(ExprPlayerPowerBoost.class,
				Double.class, "powerboost", "player");
		SimplePropertyExpression.register(ExprFactionMOTD.class, String.class,
				"motd", "faction");
		SimplePropertyExpression.register(ExprFactionHome.class,
				Location.class, "home", "faction");
		SimplePropertyExpression.register(ExprFactionTitle.class, String.class,
				"title", "player");
		SimplePropertyExpression.register(ExprSetFactionPower.class,
				Double.class, "power", "faction");
		Skript.registerExpression(ExprFactionClaim.class, Faction.class,
				ExpressionType.SIMPLE, "[the] faction at %location%");
		SimplePropertyExpression.register(ExprSetFactionPowerBoost.class,
				Double.class, "powerboost", "faction");
		Skript.registerExpression(ExprFactionList.class, String.class,
				ExpressionType.SIMPLE, "list of [all] factions",
				"factions list", "all factions");
		Skript.registerExpression(ExprFactionAllyList.class, String.class,
				ExpressionType.SIMPLE,
				"list of [all] allies of [the faction] %faction%",
				"[all] faction allies list of [the faction] %faction%");
		Skript.registerExpression(ExprPlayerList.class, Player.class,
				ExpressionType.SIMPLE,
				"list of [all] players of [the faction] %faction%",
				"[all] players list of [the faction] %faction%");
		Skript.registerExpression(
				ExprRelationShipStatus.class,
				Rel.class,
				ExpressionType.SIMPLE,
				"relation[ship] [status] between [the faction] %faction% (and|with) [the faction] %faction%");
		Skript.registerExpression(ExprRankOfPlayer.class, Rel.class,
				ExpressionType.SIMPLE, "role of [the player] %player%");
		Skript.registerExpression(ExprFactionEnemyList.class, String.class,
				ExpressionType.SIMPLE,
				"list of [all] enemies of [the faction] %faction%",
				"[all] faction enemies list of %faction%");
		Skript.registerExpression(ExprFactionTruceList.class, String.class,
				ExpressionType.SIMPLE,
				"list of [all] truces of [the faction] %faction%",
				"[all] faction truces list of %faction%");
		registerFactionsTypes();
	}

	public static void registerFactionsTypes() {

		Classes.registerClass(new ClassInfo<Faction>(Faction.class, "faction")
				.user("faction")
				.name("Faction")
				.defaultExpression(
						new EventValueExpression<Faction>(Faction.class))
				.parser(new Parser<Faction>() {
					@Override
					@Nullable
					public Faction parse(String s, ParseContext context) {
						return FactionColl.get().getByName(s);
					}

					@Override
					public String toString(Faction faction, int flags) {
						return faction.getName().toLowerCase();
					}

					@Override
					public String toVariableNameString(Faction faction) {
						return faction.getName().toLowerCase();
					}

					@Override
					public String getVariableNamePattern() {
						return ".+";
					}

				}));
		Classes.registerClass(new ClassInfo<Rel>(Rel.class, "rel").name("Rel")
				.parser(new Parser<Rel>() {
					@Override
					@Nullable
					public Rel parse(String s, ParseContext context) {
						return Rel.parse(s);
					}

					@Override
					public String toString(Rel rel, int flags) {
						return rel.toString().toLowerCase();
					}

					@Override
					public String toVariableNameString(Rel rel) {
						return rel.toString().toLowerCase();
					}

					@Override
					public String getVariableNamePattern() {
						return ".+";
					}

				}));
	}

	public static void registerAllmcMMO() {

		Skript.registerEffect(EffSendPartyMessage.class,
				"send %string% to [the] party %party% from [a] player named %string%");

		Skript.registerEffect(EffSendAdminMesssage.class,
				"send %string% to [the] admin[chat| chat] from [a] player named %string%");

		SimplePropertyExpression.register(ExprPowerLevel.class, Integer.class,
				"power(level| level)", "player");

		Skript.registerExpression(ExprLevel.class, Integer.class,
				ExpressionType.SIMPLE, "[mcmmo] %skill% level of %player%");
		PropertyCondition.register(CondIsUsingPartyChat.class,
				"(using party(chat| chat))", "players");
		PropertyCondition.register(CondIsUsingAdminChat.class,
				"(using admin(chat| chat))", "players");
		registermcMMOTypes();
	}

	public static void registermcMMOTypes() {

		Classes.registerClass(new ClassInfo<Party>(Party.class, "party").name(
				"Party").parser(new Parser<Party>() {
			@Override
			@Nullable
			public Party parse(String s, ParseContext context) {
				return PartyManager.getParty(s);
			}

			@Override
			public String toString(Party party, int flags) {
				return party.getName().toLowerCase();
			}

			@Override
			public String toVariableNameString(Party party) {
				return party.getName().toLowerCase();
			}

			@Override
			public String getVariableNamePattern() {
				return ".+";
			}

		}));

		Classes.registerClass(new ClassInfo<SkillType>(SkillType.class, "skill")
				.name("Skill").parser(new Parser<SkillType>() {
					@Override
					@Nullable
					public SkillType parse(String s, ParseContext context) {
						try {

							return SkillType.valueOf(s.toUpperCase());
						} catch (Exception e) {

						}
						return null;
					}

					@Override
					public String toString(SkillType skill, int flags) {
						return skill.getName().toLowerCase();
					}

					@Override
					public String toVariableNameString(SkillType skill) {
						return skill.getName().toLowerCase();
					}

					@Override
					public String getVariableNamePattern() {
						return ".+";
					}

				}));

	}

	public static void registerAllWorldGuard() {
		Skript.registerEvent("Region Enter", SimpleEvent.class,
				EvtRegionEnterEvent.class,
				new String[] { "[protected |protected]region enter" });

		EventValues.registerEventValue(EvtRegionEnterEvent.class, Player.class,
				new Getter<Player, EvtRegionEnterEvent>() {
					@Override
					public Player get(EvtRegionEnterEvent event) {
						return event.getPlayer();
					}
				}, 0);
		EventValues.registerEventValue(EvtRegionEnterEvent.class,
				ProtectedRegion.class,
				new Getter<ProtectedRegion, EvtRegionEnterEvent>() {
					@Override
					public ProtectedRegion get(EvtRegionEnterEvent event) {
						return event.getRegion();
					}
				}, 0);
		Skript.registerExpression(ExprRegionAtLocation.class,
				ProtectedRegion.class, ExpressionType.SIMPLE,
				"region[s] at %location%");
		Skript.registerExpression(ExprMembersOfRegion.class,
				OfflinePlayer.class, ExpressionType.SIMPLE,
				"[list of] members of [the] [region] %protectedregion%");
		Skript.registerExpression(ExprOwnersOfRegion.class,
				OfflinePlayer.class, ExpressionType.SIMPLE,
				"[list of] owners of [the] [region] %protectedregion%");
		Skript.registerExpression(ExprFlagsOfRegion.class, Flag.class,
				ExpressionType.SIMPLE,
				"[list of] flags of [the] [region] %protectedregion%");
		Skript.registerExpression(ExprFlagOfRegion.class, String.class,
				ExpressionType.SIMPLE,
				"[the] flag %flag% of [the] [region] %protectedregion%");
		registerWorldGuardTypes();
	}

	@SuppressWarnings("rawtypes")
	public static void registerWorldGuardTypes() {
		Classes.registerClass(new ClassInfo<ProtectedRegion>(
				ProtectedRegion.class, "protectedregion")
				.name("Protected Region")
				.user("protectedregions?")
				.defaultExpression(
						new EventValueExpression<ProtectedRegion>(
								ProtectedRegion.class))
				.parser(new Parser<ProtectedRegion>() {
					@Override
					@Nullable
					public ProtectedRegion parse(String s, ParseContext context) {
						for (World w : Bukkit.getServer().getWorlds()) {
							if (WGBukkit.getRegionManager(w).hasRegion(s))
								return WGBukkit.getRegionManager(w)
										.getRegion(s);
						}
						return null;
					}

					@Override
					public String toString(ProtectedRegion region, int flags) {
						return region.getId().toLowerCase();
					}

					@Override
					public String toVariableNameString(ProtectedRegion region) {
						return region.getId().toLowerCase();
					}

					@Override
					public String getVariableNamePattern() {
						return ".+";
					}

				}));
		Classes.registerClass(new ClassInfo<Flag>(Flag.class, "flag").name(
				"Flag").parser(new Parser<Flag<?>>() {
			@Override
			public Flag<?> parse(String s, ParseContext context) {
				return DefaultFlag.fuzzyMatchFlag(s);
			}

			@Override
			public String toString(Flag<?> flag, int flags) {
				return flag.getName().toLowerCase();
			}

			@Override
			public String toVariableNameString(Flag<?> flag) {
				return flag.getName().toLowerCase();
			}

			@Override
			public String getVariableNamePattern() {
				return ".+";
			}

		}));

	}

}
