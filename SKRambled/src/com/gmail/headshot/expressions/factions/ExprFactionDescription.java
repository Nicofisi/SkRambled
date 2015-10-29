package com.gmail.headshot.expressions.factions;

import org.bukkit.event.Event;

import com.massivecraft.factions.entity.Faction;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;

public class ExprFactionDescription extends
		SimplePropertyExpression<Faction, String> {

	@Override
	public Class<? extends String> getReturnType() {

		return String.class;
	}

	@Override
	public String convert(Faction fac) {
		return fac.getDescription();
	}

	@Override
	protected String getPropertyName() {
		return "faction description";
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Faction fac = getExpr().getSingle(e);
		if (fac == null)
			return;
		String desc = (String) (delta[0]);
		if (mode == Changer.ChangeMode.SET) {
			fac.setDescription(desc);
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
