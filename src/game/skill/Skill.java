package game.skill;

import game.combat.TargetType;
import game.core.Player;
import game.resources.Mana;

public abstract class Skill {

    protected String name;
    protected int cost;
    protected TargetType targetType;

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    /* ==== LÓGICA PADRÃO ==== */

    public boolean canUse(Player actor) {
        Mana mana = actor.getResource(Mana.class);
        return mana != null && mana.hasEnough(cost);
    }

    public void consume(Player actor) {
        Mana mana = actor.getResource(Mana.class);
        if (mana != null)
            mana.spend(cost);
    }

    public abstract void use(Player actor, Player target);
}
