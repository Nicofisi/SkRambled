package com.gmail.headshot.Events.factions;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.massivecraft.factions.event.EventFactionsDisband;

public class FactionDisbandEvent implements Listener {

	@EventHandler
	public void onFactionDisband(EventFactionsDisband event) {

		CommandSender sender = event.getSender();
		String factionname = event.getFaction().getName();

		if (sender instanceof Player) {
			Player player = (Player) sender;
			Bukkit.getServer().getPluginManager()
					.callEvent(new EvtFactionDisbandEvent(player, factionname));

		}

	}

}
