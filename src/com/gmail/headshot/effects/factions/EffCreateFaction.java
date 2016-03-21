package com.gmail.headshot.effects.factions;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.massivecraft.factions.Rel;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.FactionColl;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.factions.entity.MPlayerColl;
import com.massivecraft.factions.event.EventFactionsCreate;
import com.massivecraft.factions.event.EventFactionsMembershipChange;
import com.massivecraft.factions.event.EventFactionsMembershipChange.MembershipChangeReason;
import com.massivecraft.massivecore.store.MStore;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

public class EffCreateFaction extends Effect {

	private Expression<String> factionname;
	private Expression<Player> player;

	@Override
	protected void execute(Event event) {
		String factionname = this.factionname.getSingle(event);
		Player player = this.player.getSingle(event);
		if (factionname == null)
			return;
		if (player == null)
			return;
		if (FactionColl.get().isNameTaken(factionname)) {
			return;
		}
		String factionId = MStore.createId();
		MPlayer mplayer = MPlayerColl.get().get(player);
		Faction oldfac = mplayer.getFaction();
		oldfac.detach();
		EventFactionsCreate createEvent = new EventFactionsCreate(player,
				factionId, factionname);
		createEvent.run();
		if (createEvent.isCancelled())
			return;
		Faction faction = FactionColl.get().create(factionId);
		faction.setName(factionname);

		mplayer.setRole(Rel.LEADER);
		mplayer.setFaction(faction);

		EventFactionsMembershipChange joinEvent = new EventFactionsMembershipChange(
				player, mplayer, faction, MembershipChangeReason.CREATE);
		joinEvent.run();
	}

	@Override
	public String toString(Event event, boolean b) {
		return "faction ally faction";
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int i, Kleenean kleenean,
			SkriptParser.ParseResult parseResult) {
		this.factionname = (Expression<String>) expressions[0];
		this.player = (Expression<Player>) expressions[1];
		return true;
	}

}
