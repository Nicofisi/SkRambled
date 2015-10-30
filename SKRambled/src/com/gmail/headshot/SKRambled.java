package com.gmail.headshot;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.headshot.events.factions.FactionCreateEvent;
import com.gmail.headshot.events.factions.FactionDescriptionChangeEvent;
import com.gmail.headshot.events.factions.FactionDisbandEvent;
import com.gmail.headshot.events.factions.FactionNameChangeEvent;

import ch.njol.skript.Skript;

public class SKRambled extends JavaPlugin {
	static SKRambled instance;
	public File file;

	public void onEnable() {
		instance = this;
		Plugin skript = Bukkit.getServer().getPluginManager()
				.getPlugin("Skript");
		if (skript != null) {
			getLogger()
					.info("[SKRambled] Congratulations! It is official.. SKRambled has been enabled!");
			Skript.registerAddon(this);
			getLogger().info("[SKRambled] Skript has been hooked!");
			Plugin mcMMO = Bukkit.getServer().getPluginManager()
					.getPlugin("mcMMO");
			if (mcMMO != null) {

				getLogger().info("[SKRambled] Successfully found mcMMO!");
				Register.registerAllmcMMO();
			} else {

				getLogger().info("[SKRambled] Could not find mcMMO!");

			}
			Plugin MassiveCore = Bukkit.getServer().getPluginManager()
					.getPlugin("MassiveCore");
			if (MassiveCore != null) {
				getLogger()
						.info("[SKRambled] Successfully found MassiveCore, Now you can use Factions API.");
				Plugin faction = Bukkit.getServer().getPluginManager()
						.getPlugin("Factions");
				if (faction != null) {
					getLogger().info(
							"[SKRambled] Successfully hooked to Factions API!");
					getServer().getPluginManager().registerEvents(
							new FactionCreateEvent(), this);
					getServer().getPluginManager().registerEvents(
							new FactionDisbandEvent(), this);
					getServer().getPluginManager().registerEvents(
							new FactionNameChangeEvent(), this);
					getServer().getPluginManager().registerEvents(
							new FactionDescriptionChangeEvent(), this);
					Register.registerAllFactions();
				}
			} else {

				getLogger().info("[SKRambled] Unabled to find MassiveCore.");

			}
		} else {

			getLogger().info(
					"[SKRambled] Skript was not found, Disabling plugin.");
			Bukkit.getServer().getPluginManager().getPlugin("SKRambled");
		}

	}

	public void onDisable() {

		getLogger().info("[SKRambled] Well, it seems like I was disabled..");

	}

	public static SKRambled getInstance() {
		return instance;
	}
}
