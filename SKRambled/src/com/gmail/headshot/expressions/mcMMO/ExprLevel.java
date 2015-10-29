package com.gmail.headshot.expressions.mcMMO;

import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.skills.SkillType;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExprLevel extends SimpleExpression<Integer> {
	private Expression<Player> player;
	private Expression<SkillType> skill;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(final Expression<?>[] exprs, final int matchedPattern,
			final Kleenean isDelayed, final ParseResult parseResult) {

		skill = (Expression<SkillType>) exprs[0];
		player = (Expression<Player>) exprs[1];

		return true;
	}

	@Override
	protected Integer[] get(final Event e) {
		SkillType skill = this.skill.getSingle(e);
		Player player = this.player.getSingle(e);

		return new Integer[] { new Integer(ExperienceAPI.getLevel(player,
				skill.getName())) };

	}

	@Override
	public boolean isSingle() {
		return false;
	}

	@Override
	public Class<? extends Integer> getReturnType() {
		return Integer.class;
	}

	@Override
	public String toString(final @Nullable Event e, final boolean debug) {
		return "mcmmo level";
	}

	public void change(Event e, Object[] delta, Changer.ChangeMode mode) {
		SkillType skill = this.skill.getSingle(e);
		Player player = this.player.getSingle(e);
		if (skill == null)
			return;
		if (player == null)
			return;
		Integer level = (Integer) (delta[0]);
		if (mode == Changer.ChangeMode.SET) {
			ExperienceAPI.setLevel(player, skill.getName(), level);
		}
		if (mode == Changer.ChangeMode.ADD) {
			ExperienceAPI.setLevel(player, skill.getName(), level
					+ ExperienceAPI.getLevel(player, skill.getName()));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == Changer.ChangeMode.SET)
			return CollectionUtils.array(Integer.class);
		if (mode == Changer.ChangeMode.ADD)
			return CollectionUtils.array(Integer.class);
		return null;
	}
}
