package com.gmail.headshot.Events.factions;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class EvtFactionDisbandEvent extends Event {

	Player player;
	String facname;

	public EvtFactionDisbandEvent(Player player, String facname) {
		this.player = player;
		this.facname = facname;
	}

	public Player getPlayer() {
		return player;
	}

	public String getFacName() {
		return facname;
	}

	private static final HandlerList handlers = new HandlerList();

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

}
