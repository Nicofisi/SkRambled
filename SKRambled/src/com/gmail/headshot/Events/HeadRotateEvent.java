package com.gmail.headshot.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class HeadRotateEvent implements Listener {
	@EventHandler
	public void onHeadRotate(PlayerMoveEvent event) {
		if ((event.getFrom().getYaw() != event.getTo().getYaw())
				&& event.getFrom().getPitch() != event.getTo().getPitch()) {
			Player player = event.getPlayer();
			EvtHeadRotateEvent e = new EvtHeadRotateEvent(player);
			Bukkit.getServer().getPluginManager().callEvent(e);
			if (e.isCancelled()) {
				event.getTo().setPitch(
						event.getTo().getPitch()
								- (event.getTo().getPitch() - event.getFrom()
										.getPitch()));
				event.getTo().setYaw(
						event.getTo().getYaw()
								- (event.getTo().getYaw() - event.getFrom()
										.getYaw()));
				return;
			}
		}

	}

}
