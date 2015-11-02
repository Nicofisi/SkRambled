package com.gmail.headshot.expressions.factions;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.massivecraft.factions.entity.MPlayer;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;

public class ExprPlayerPowerBoost extends
		SimplePropertyExpression<Player, Double> {

	@Override
	public Class<? extends Double> getReturnType() {

		return Double.class;
	}

	@Override
	public Double convert(Player player) {
		MPlayer mplayer = MPlayer.get(player);
		return mplayer.getPowerBoost();
	}

	@Override
	protected String getPropertyName() {
		return "faction power";
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Player player = getExpr().getSingle(e);
		MPlayer mplayer = MPlayer.get(player);
		if (player == null)
			return;
		double power = ((Number) delta[0]).doubleValue();
		if (mode == Changer.ChangeMode.SET) {
			mplayer.setPowerBoost(power);
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
