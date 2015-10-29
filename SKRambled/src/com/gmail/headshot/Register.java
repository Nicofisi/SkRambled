package com.gmail.headshot;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;

import com.gmail.headshot.Events.factions.EvtFactionCreateEvent;
import com.gmail.headshot.Events.factions.EvtFactionDescriptionChangeEvent;
import com.gmail.headshot.Events.factions.EvtFactionDisbandEvent;
import com.gmail.headshot.Events.factions.EvtFactionNameChangeEvent;
import com.gmail.headshot.conditions.factions.CondIsLeader;
import com.gmail.headshot.conditions.factions.CondIsMember;
import com.gmail.headshot.conditions.factions.CondIsOfficer;
import com.gmail.headshot.conditions.factions.CondIsRecruit;
import com.gmail.headshot.effects.factions.EffClaimLand;
import com.gmail.headshot.effects.factions.EffCreateFaction;
import com.gmail.headshot.effects.factions.EffDisbandFaction;
import com.gmail.headshot.effects.factions.EffInvitePlayer;
import com.gmail.headshot.effects.factions.EffKickPlayer;
import com.gmail.headshot.effects.factions.EffPromotePlayer;
import com.gmail.headshot.effects.factions.EffUnClaimLand;
import com.gmail.headshot.expressions.factions.ExprFactionAllyList;
import com.gmail.headshot.expressions.factions.ExprFactionClaim;
import com.gmail.headshot.expressions.factions.ExprFactionDescription;
import com.gmail.headshot.expressions.factions.ExprFactionEnemyList;
import com.gmail.headshot.expressions.factions.ExprFactionHome;
import com.gmail.headshot.expressions.factions.ExprFactionList;
import com.gmail.headshot.expressions.factions.ExprFactionMOTD;
import com.gmail.headshot.expressions.factions.ExprFactionName;
import com.gmail.headshot.expressions.factions.ExprFactionPower;
import com.gmail.headshot.expressions.factions.ExprFactionPowerBoost;
import com.gmail.headshot.expressions.factions.ExprFactionTitle;
import com.gmail.headshot.expressions.factions.ExprFactionTruceList;
import com.gmail.headshot.expressions.factions.ExprPlayerFaction;
import com.gmail.headshot.expressions.factions.ExprPlayerList;
import com.gmail.headshot.expressions.factions.ExprRelationShipStatus;
import com.gmail.headshot.expressions.factions.ExprSetFactionPower;
import com.gmail.headshot.expressions.factions.ExprSetFactionPowerBoost;
import com.gmail.headshot.expressions.mcMMO.ExprLevel;
import com.gmail.headshot.expressions.mcMMO.ExprPowerLevel;
import com.gmail.nossr50.datatypes.skills.SkillType;
import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;

public class Register {

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
		Skript.registerEffect(EffPromotePlayer.class,
				"promote %player% to %rel%");
		SimplePropertyExpression.register(ExprFactionName.class, String.class,
				"name", "faction");
		SimplePropertyExpression.register(ExprPlayerFaction.class,
				Faction.class, "faction", "player");
		SimplePropertyExpression.register(ExprFactionDescription.class,
				String.class, "description", "faction");
		SimplePropertyExpression.register(ExprFactionPower.class, Double.class,
				"power", "player");
		SimplePropertyExpression.register(ExprFactionPowerBoost.class,
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
				Double.class, "faction powerboost", "string");
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
		Skript.registerExpression(ExprFactionEnemyList.class, String.class,
				ExpressionType.SIMPLE,
				"list of [all] enemies of [the faction] %faction%",
				"[all] faction enemies list of %faction%");
		Skript.registerExpression(ExprFactionTruceList.class, String.class,
				ExpressionType.SIMPLE,
				"list of [all] truces of [the faction] %faction%",
				"[all] faction truces list of %faction%");
		Skript.registerCondition(CondIsLeader.class,
				"%player% is leader of [the faction] %faction%",
				"%player% (is not|isn't) leader of [the faction] %faction%");
		Skript.registerCondition(CondIsOfficer.class,
				"%player% is officer (of|in) [the faction] %faction%",
				"%player% (is not|isn't) officer (of|in) [the faction] %faction%");
		Skript.registerCondition(CondIsMember.class,
				"%player% is member (of|in) [the faction] %faction%",
				"%player% (is not|isn't) member (of|in) [the faction] %faction%");
		Skript.registerCondition(CondIsRecruit.class,
				"%player% is recruit (of|in) [the faction] %faction%",
				"%player% (is not|isn't) recruit (of|in) [the faction] %faction%");
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

		SimplePropertyExpression.register(ExprPowerLevel.class, Integer.class,
				"power level", "player");

		Skript.registerExpression(ExprLevel.class, Integer.class,
				ExpressionType.SIMPLE, "[mcmmo] %skill% level of %player%");
		registermcMMOTypes();
	}
	public static void registermcMMOTypes() {

		Classes.registerClass(new ClassInfo<SkillType>(SkillType.class, "skill")
				.name("Skill")
				.parser(new Parser<SkillType>() {
					@Override
					@Nullable
					public SkillType parse(String s, ParseContext context) {
						return SkillType.getSkill(s);
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

}
