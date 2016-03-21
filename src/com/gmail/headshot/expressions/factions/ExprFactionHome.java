package com.gmail.headshot.expressions.factions;

import org.bukkit.Location;
import org.bukkit.event.Event;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.ps.PS;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;

public class ExprFactionHome extends
		SimplePropertyExpression<Faction, Location> {

	@Override
	public Class<? extends Location> getReturnType() {

		return Location.class;
	}

	@Override
	public Location convert(Faction fac) {
		return fac.getHome().asBukkitLocation();
	}

	@Override
	protected String getPropertyName() {
		return "faction home";
	}

	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		Faction fac = getExpr().getSingle(e);
		if (fac == null)
			return;
		Location loc = (Location) (delta[0]);
		if (mode == Changer.ChangeMode.SET) {
			fac.setHome(PS.valueOf(loc));
		}
		if (mode == Changer.ChangeMode.REMOVE) {
			fac.setHome(null);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		return CollectionUtils.array(Location.class);
	}
}
