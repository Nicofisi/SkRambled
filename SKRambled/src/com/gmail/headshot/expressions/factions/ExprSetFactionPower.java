package com.gmail.headshot.expressions.factions;


import com.massivecraft.factions.entity.Faction;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

public class ExprSetFactionPower extends
		SimplePropertyExpression<Faction, Double> {

	@Override
	public Class<? extends Double> getReturnType() {

		return Double.class;
	}

	@Override
	public Double convert(Faction faction) {
		return faction.getPower();
	}

	@Override
	protected String getPropertyName() {
		return "faction power";
	}
}
