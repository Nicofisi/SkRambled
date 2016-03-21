package com.gmail.headshot.effects.factions;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffInvitePlayer extends Effect {

	private Expression<Player> player;
	private Expression<Faction> faction;

	@Override
	protected void execute(Event event) {
		Player player = this.player.getSingle(event);
		Faction fac = this.faction.getSingle(event);
		if (player == null) {
			return;
		} else if (fac == null) {
			return;
		}
		MPlayer mplayer = MPlayer.get(player);
		fac.setInvited(mplayer, true);

	}

	@Override
	public String toString(Event event, boolean b) {
		return "faction invite player";
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean,
			SkriptParser.ParseResult parseResult) {
		this.faction = (Expression<Faction>) expressions[1];
		this.player = (Expression<Player>) expressions[0];
		return true;
	}

}
