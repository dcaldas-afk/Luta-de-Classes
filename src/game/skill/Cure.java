package game.skill;

import java.util.Map;

import game.buffs.StatEffect;
import game.combat.CombatLog;
import game.combat.TargetType;
import game.core.Player;
import game.core.StatType;
import java.util.Random;

public class Cure extends Skill {

    public Cure() {
        this.name = "Cura";
        this.cost = 20;
        this.targetType = TargetType.ALLY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        if (!canUse(actor))
            return;
        
        Random r = new Random();
        int seed = actor.getStats().getIntelligence();
        consume(actor);
        int heal = (int) (10 + seed*2 - (r.nextInt(seed) + 10));
        target.increaseCurrentHP(heal);
        if (target.getCurrentHP() >= target.getMaxHP())
            target.setCurrentHP(target.getMaxHP());
        CombatLog.register(target.getName() + " foi curado em " + heal + "HP");
    }
    
    @Override
    public void displayMessage(Player actor, Player target) {
        if (actor == target)
            CombatLog.register(actor.getName() + " usou " + name + " em si mesmo");
        else
            CombatLog.register(actor.getName() + " usou " + name + " em " + target.getName());

    }
}
