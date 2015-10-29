package com.gmail.headshot.expressions.factions;


import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.massivecraft.factions.entity.MPlayer;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;

public class ExprFactionTitle extends SimplePropertyExpression<Player, String>{

	@Override
	public Class<? extends String> getReturnType() {
		
		return String.class;
	}

	@Override

	public String convert(Player player) {
		MPlayer mplayer = MPlayer.get(player);
		return mplayer.getTitle();
	}

	@Override
	protected String getPropertyName() {
		return "faction title";
	}
	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Player player = getExpr().getSingle(e);
		MPlayer mplayer = MPlayer.get(player);
		if(player == null)
			return;
		String title = (String) (delta[0]);
		if (mode == Changer.ChangeMode.SET){
			mplayer.setTitle(title);
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

