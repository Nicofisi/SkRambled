package com.gmail.headshot.expressions.factions;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;

public class ExprPlayerFaction extends
		SimplePropertyExpression<Player, Faction> {

	@Override
	public Class<? extends Faction> getReturnType() {

		return Faction.class;
	}

	@Override
	public Faction convert(Player player) {
		MPlayer mplayer = MPlayer.get(player);
		return mplayer.getFaction();
	}

	@Override
	protected String getPropertyName() {
		return "faction of player";
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Player player = getExpr().getSingle(e);
		MPlayer mplayer = MPlayer.get(player);
		if (player == null)
			return;
		Faction faction = (Faction) (delta[0]);
		if (mode == Changer.ChangeMode.SET) {
			mplayer.setFaction(faction);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET)
			return CollectionUtils.array(Faction.class);
		return null;
	}
}
