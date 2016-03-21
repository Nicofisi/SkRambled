package com.gmail.headshot.events.factions;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.event.EventFactionsDescriptionChange;

public class FactionDescriptionChangeEvent implements Listener {

	@EventHandler
	public void onFactionDescriptionChange(EventFactionsDescriptionChange event) {

		CommandSender sender = event.getSender();
		Faction faction = event.getFaction();
		String newdesc = event.getNewDescription();

		if (sender instanceof Player) {
			Player player = (Player) sender;
			Bukkit.getServer()
					.getPluginManager()
					.callEvent(
							new EvtFactionDescriptionChangeEvent(player,
									faction, newdesc));

		}

	}

}
