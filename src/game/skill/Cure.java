package game.skill;

import java.util.Map;

import game.buffs.StatEffect;
import game.combat.CombatLog;
import game.combat.TargetType;
import game.core.Player;
import game.core.StatType;

public class Cure extends Skill {

    public Cure() {
        this.name = "Cura";
        this.cost = 15;
        this.targetType = TargetType.ALLY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        if (!canUse(actor))
            return;

        consume(actor);
        target.increaseCurrentHP(30);
        if (target.getCurrentHP() >= target.getMaxHP())
            target.setCurrentHP(target.getMaxHP());
        CombatLog.register(target.getName() + " foi curado em 30 HP");
    }
    
    @Override
    public void displayMessage(Player actor, Player target) {
        if (actor == target)
            CombatLog.register(actor.getName() + " usou " + name + " em si mesmo");
        else
            CombatLog.register(actor.getName() + " usou " + name + " em " + target.getName());

    }
}
