package com.gmail.headshot.events.factions;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.event.EventFactionsNameChange;

public class FactionNameChangeEvent implements Listener {

	@EventHandler
	public void onFactionNameChange(EventFactionsNameChange event) {

		CommandSender sender = event.getSender();
		Faction faction = event.getFaction();

		if (sender instanceof Player) {
			Player player = (Player) sender;
			Bukkit.getServer().getPluginManager()
					.callEvent(new EvtFactionNameChangeEvent(player, faction));

		}

	}

}
