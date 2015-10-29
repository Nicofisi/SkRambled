package com.gmail.headshot.expressions.factions;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExprRelationShipStatus extends SimpleExpression<Rel> {

	private Expression<Faction> fac1;
	private Expression<Faction> fac2;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(final Expression<?>[] exprs, final int matchedPattern,
			final Kleenean isDelayed, final ParseResult parseResult) {
		fac1 = (Expression<Faction>) exprs[0];
		fac2 = (Expression<Faction>) exprs[1];
		return true;
	}

	@Override
	protected Rel[] get(final Event e) {
		Faction firstfac = fac1.getSingle(e);
		Faction secondfac = fac2.getSingle(e);
		if (firstfac == null)
			return null;
		if (secondfac == null)
			return null;
		Rel r = firstfac.getRelationTo(secondfac);
		return new Rel[] { r };
	}

	@Override
	public Class<? extends Rel> getReturnType() {
		return Rel.class;
	}
	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Faction firstfac = fac1.getSingle(e);
		Faction secondfac = fac2.getSingle(e);
		if (firstfac == null)
			return;
		if (secondfac == null)
			return;
		Rel rel = (Rel) (delta[0]);
		if (mode == Changer.ChangeMode.SET) {
			firstfac.setRelationWish(secondfac, rel);
		}
		if (mode == Changer.ChangeMode.REMOVE) {
			firstfac.setRelationWish(secondfac, Rel.NEUTRAL);
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
