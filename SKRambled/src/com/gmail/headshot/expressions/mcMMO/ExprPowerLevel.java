package com.gmail.headshot.expressions.mcMMO;

import org.bukkit.entity.Player;

import com.gmail.nossr50.api.ExperienceAPI;

import ch.njol.skript.expressions.base.SimplePropertyExpression;

public class ExprPowerLevel extends SimplePropertyExpression<Player, Integer> {

	@Override
	public Class<? extends Integer> getReturnType() {

		return Integer.class;
	}

	@Override
	public Integer convert(Player player) {

		return ExperienceAPI.getPowerLevel(player);

	}

	@Override
	protected String getPropertyName() {
		return "power level";
	}
}
