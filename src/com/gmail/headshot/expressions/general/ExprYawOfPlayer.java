package com.gmail.headshot.expressions.general;

import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExprYawOfPlayer extends SimpleExpression<Float> {
	private Expression<Player> player;

	@Override
	public Class<? extends Float> getReturnType() {
		return Float.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] exprs, int arg1, Kleenean arg2,
			ParseResult arg3) {
		player = (Expression<Player>) exprs[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "yaw of %player%";
	}

	@Override
	@Nullable
	protected Float[] get(Event e) {
		Player player = this.player.getSingle(e);
		if (player == null)
			return null;
		return new Float[] { player.getLocation().getYaw() };
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Player player = this.player.getSingle(e);
		if (player == null)
			return;
		Float yaw = (Float) (delta[0]);
		if (mode == Changer.ChangeMode.SET) {

			Location loc = player.getLocation();
			loc.setYaw(yaw);
			player.teleport(loc);

		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		return CollectionUtils.array(Float.class);
	}
}
