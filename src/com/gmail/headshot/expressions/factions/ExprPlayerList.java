package com.gmail.headshot.expressions.factions;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprPlayerList extends SimpleExpression<Player> {
	private Expression<Faction> faction;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(final Expression<?>[] exprs, final int matchedPattern,
			final Kleenean isDelayed, final ParseResult parseResult) {
		faction = (Expression<Faction>) exprs[0];
		return true;
	}

	@Override
	protected Player[] get(final Event e) {
		List<Player> players = new ArrayList<Player>();
		Faction faction = this.faction.getSingle(e);
		for (MPlayer p : faction.getMPlayers()) {
			players.add(p.getPlayer());
		}
		return (Player[]) players.toArray();

	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Player> getReturnType() {
		return Player.class;
	}

	@Override
	public String toString(final @Nullable Event e, final boolean debug) {
		return "faction player list";
	}
}
