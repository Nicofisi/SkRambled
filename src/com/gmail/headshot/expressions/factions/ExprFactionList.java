package com.gmail.headshot.expressions.factions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;
import org.bukkit.event.Event;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprFactionList extends SimpleExpression<String> {

	final Collection<Faction> factions = FactionColl.get().getAll();
	List<String> factionnames = new ArrayList<String>();

	@Override
	public boolean init(final Expression<?>[] exprs, final int matchedPattern,
			final Kleenean isDelayed, final ParseResult parseResult) {
		return true;
	}

	@Override
	protected String[] get(final Event e) {
		factionnames.clear();
		for (Faction fac : factions) {

			try {

				factionnames.add(fac.getName());
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

		return factionnames.toArray(new String[factionnames.size()]);

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
		return "faction list";
	}
}
