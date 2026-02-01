package game.skill;

import java.util.List;

import game.combat.CombatLog;
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

    public boolean isMute(Player actor) {
        return actor.isSilenced();
    }

    public abstract void use(Player actor, Player target);

    public abstract void displayMessage(Player actor, Player target);
}
