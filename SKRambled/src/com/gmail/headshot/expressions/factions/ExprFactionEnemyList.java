package com.gmail.headshot.expressions.factions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.event.Event;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprFactionEnemyList extends SimpleExpression<String> {
	private Expression<Faction> faction;
	final Collection<Faction> factions = FactionColl.get().getAll();
	List<String> factionallies = new ArrayList<String>();

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(final Expression<?>[] exprs, final int matchedPattern,
			final Kleenean isDelayed, final ParseResult parseResult) {
		faction = (Expression<Faction>) exprs[0];
		return true;
	}

	@Override
	protected String[] get(final Event e) {
		factionallies.clear();
		Faction thefac = this.faction.getSingle(e);
		for (Faction fac : factions) {
			if (fac != null) {
				try {
					if (thefac.getRelationTo(fac) == Rel.ENEMY
							&& fac.getName() != "SafeZone"
							&& fac.getName() != "WarZone") {
						factionallies.add(fac.getName());
					}
				} catch (Exception e2) {

				}
			}
		}
		return factionallies.toArray(new String[factionallies.size()]);

	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public String toString(final @Nullable Event e, final boolean debug) {
		return "faction ally list";
	}
}
