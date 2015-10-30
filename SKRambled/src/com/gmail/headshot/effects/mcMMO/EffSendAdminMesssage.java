package com.gmail.headshot.effects.mcMMO;

import org.bukkit.event.Event;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import com.gmail.headshot.SKRambled;
import com.gmail.nossr50.api.ChatAPI;

public class EffSendAdminMesssage extends Effect {
	private Expression<String> sender;
	private Expression<String> message;

	@Override
	protected void execute(Event event) {

		String sender = this.sender.getSingle(event);
		String message = this.message.getSingle(event);

		if (sender == null) {
			return;
		}
		if (message == null) {
			return;
		}
		ChatAPI.sendAdminChat(SKRambled.getInstance(), sender, message);
	}

	@Override
	public String toString(Event event, boolean b) {
		return "faction claim location";
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean init(Expression<?>[] expressions, int matchedPattern,
			Kleenean kleenean, SkriptParser.ParseResult parseResult) {
		this.sender = (Expression<String>) expressions[1];
		this.message = (Expression<String>) expressions[0];
		return true;
	}
}
