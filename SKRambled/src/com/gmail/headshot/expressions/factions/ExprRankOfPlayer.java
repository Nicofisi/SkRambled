package com.gmail.headshot.expressions.factions;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.entity.MPlayerColl;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExprRankOfPlayer extends SimpleExpression<Rel> {

	private Expression<Player> player;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(final Expression<?>[] exprs, final int matchedPattern,
			final Kleenean isDelayed, final ParseResult parseResult) {
		player = (Expression<Player>) exprs[0];
		return true;
	}

	@Override
	protected Rel[] get(final Event e) {
		Player player = this.player.getSingle(e);
		if (player == null)
			return null;
		MPlayer mplayer = MPlayerColl.get().get(player);
		Rel r = mplayer.getRole();
		return new Rel[] { r };
	}

	@Override
	public Class<? extends Rel> getReturnType() {
		return Rel.class;
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Player player = this.player.getSingle(e);

		if (player == null)
			return;
		MPlayer mplayer = MPlayerColl.get().get(player);
		Rel rel = (Rel) (delta[0]);
		if (mode == Changer.ChangeMode.SET) {
			mplayer.setRole(rel);
		}
		if (mode == Changer.ChangeMode.REMOVE) {
			mplayer.setRole(Rel.RECRUIT);
		}
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public String toString(final @Nullable Event e, final boolean debug) {
		return "relation between faction";
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		return CollectionUtils.array(Rel.class);
	}
}
