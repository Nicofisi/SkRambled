package com.gmail.headshot.effects.factions;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.event.Event;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.massivecore.ps.PS;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffClaimLand extends Effect {

	private Expression<Location> location;
	private Expression<Faction> faction;

	@Override
	protected void execute(Event event) {

		Location location = this.location.getSingle(event);
		Faction faction = this.faction.getSingle(event);

		if (faction == null) {
			return;
		} else if (location == null) {
			return;
		}
		Chunk chunk = location.getWorld().getChunkAt(location);
		BoardColl.get().setFactionAt(PS.valueOf(chunk), faction);
	}

	@Override
	public String toString(Event event, boolean b) {
		return "faction claim location";
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean,
			SkriptParser.ParseResult parseResult) {
		this.location = (Expression<Location>) expressions[1];
		this.faction = (Expression<Faction>) expressions[0];
		return true;
	}

}
