package com.gmail.headshot.conditions.factions;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class CondIsMember extends Condition {

	private Expression<Player> player;
	private Expression<Faction> faction;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expression, int paramInt,
			Kleenean paramKleenean, ParseResult paramParseResult) {
		player = (Expression<Player>) expression[0];
		faction = (Expression<Faction>) expression[1];
		setNegated(paramInt == 1);
		return true;
	}

	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "relation of faction";
	}

	@Override
	public boolean check(Event e) {
		Player player = this.player.getSingle(e);
		MPlayer mplayer = MPlayer.get(player);
		Faction fac = this.faction.getSingle(e);
		return isNegated() ? !(mplayer.getFaction() == fac && mplayer.getRole() == Rel.MEMBER)
				: (mplayer.getFaction() == fac && mplayer.getRole() == Rel.MEMBER);

	}

}
