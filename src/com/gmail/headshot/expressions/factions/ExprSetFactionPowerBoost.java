package com.gmail.headshot.expressions.factions;

import org.bukkit.event.Event;

import com.massivecraft.factions.entity.Faction;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;

public class ExprSetFactionPowerBoost extends
		SimplePropertyExpression<Faction, Double> {

	@Override
	public Class<? extends Double> getReturnType() {

		return Double.class;
	}

	@Override
	public Double convert(Faction fac) {
		return fac.getPowerBoost();
	}

	@Override
	protected String getPropertyName() {
		return "faction powerboost";
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Faction faction = getExpr().getSingle(e);
		if (faction == null)
			return;
		double power = ((Number) delta[0]).doubleValue();
		if (mode == Changer.ChangeMode.SET) {
			faction.setPowerBoost(power);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET)
			return CollectionUtils.array(Number.class);
		return null;
	}
}
