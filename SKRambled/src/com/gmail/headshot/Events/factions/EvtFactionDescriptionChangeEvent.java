package com.gmail.headshot.Events.factions;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.massivecraft.factions.entity.Faction;

public class EvtFactionDescriptionChangeEvent extends Event {

	Player player;
	Faction fac;
	String newdesc;

	public EvtFactionDescriptionChangeEvent(Player player, Faction fac,
			String newdesc) {
		this.player = player;
		this.fac = fac;
		this.newdesc = newdesc;
	}

	public Player getPlayer() {
		return player;
	}

	public Faction getFac() {
		return fac;
	}

	public String getNewDesc() {
		return newdesc;
	}

	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
