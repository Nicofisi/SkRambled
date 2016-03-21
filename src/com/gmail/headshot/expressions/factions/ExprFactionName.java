package com.gmail.headshot.expressions.factions;

import org.bukkit.event.Event;

import com.massivecraft.factions.entity.Faction;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;

public class ExprFactionName extends SimplePropertyExpression<Faction, String> {

	@Override
	public Class<? extends String> getReturnType() {

		return String.class;
	}

	@Override
	public String convert(Faction fac) {
		return fac.getName();
	}

	@Override
	protected String getPropertyName() {
		return "faction name";
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Faction faction = getExpr().getSingle(e);
		if (faction == null)
			return;
		String name = (String) (delta[0]);
		if (mode == Changer.ChangeMode.SET) {
			faction.setName(name);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET)
			return CollectionUtils.array(String.class);
		return null;
	}
}
