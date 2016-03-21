package com.gmail.headshot.conditions.mcMMO;

import org.bukkit.entity.Player;

import com.gmail.nossr50.api.ChatAPI;

import ch.njol.skript.conditions.base.PropertyCondition;

public class CondIsUsingPartyChat extends PropertyCondition<Player> {

	@Override
	public boolean check(final Player player) {
		return ChatAPI.isUsingPartyChat(player.getName());
	}

	@Override
	protected String getPropertyName() {
		return "using partychat";
	}

}
