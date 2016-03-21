package com.gmail.headshot.expressions.factions;

import org.bukkit.Location;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.ps.PS;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprFactionClaim extends SimpleExpression<Faction> {
	private Expression<Location> location;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(final Expression<?>[] exprs, final int matchedPattern,
			final Kleenean isDelayed, final ParseResult parseResult) {
		location = (Expression<Location>) exprs[0];
		return true;
	}

	@Override
	protected Faction[] get(final Event e) {
		Location loc = this.location.getSingle(e);
		Faction faction = BoardColl.get().getFactionAt(PS.valueOf(loc));

		return new Faction[] { faction };

	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends Faction> getReturnType() {
		return Faction.class;
	}

	@Override
	public String toString(final @Nullable Event e, final boolean debug) {
		return "faction ally list";
	}
}
