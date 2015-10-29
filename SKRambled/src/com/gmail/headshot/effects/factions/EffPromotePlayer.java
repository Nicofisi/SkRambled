package com.gmail.headshot.effects.factions;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.MPlayer;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffPromotePlayer extends Effect {

	private Expression<Player> player;
	private Expression<Rel> rel;

	@Override
	protected void execute(Event event) {

		Player player = this.player.getSingle(event);
		Rel role = this.rel.getSingle(event);

		if (player == null) {
			return;
		}

		MPlayer mplayer = MPlayer.get(player);
		if (role.equals(Rel.LEADER) || role.equals(Rel.OFFICER)
				|| role.equals(Rel.RECRUIT) || role.equals(Rel.MEMBER)) {
			mplayer.setRole(role);
		} else {

			Skript.error("You can only promote a player to leader or officer or member or recruit");

		}
	}

	@Override
	public String toString(Event event, boolean b) {
		return "faction ally faction";
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean,
			SkriptParser.ParseResult parseResult) {
		this.player = (Expression<Player>) expressions[0];
		this.rel = (Expression<Rel>) expressions[1];
		return true;
	}

}
