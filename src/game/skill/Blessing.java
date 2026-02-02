package game.skill;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class Blessing extends Skill {

    public Blessing() {
        this.name = "Bênção";
        this.cost = 15;
        this.targetType = TargetType.ALLY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {

        boolean applied = target.addEffect(new BleesingEffect());

        consume(actor);
        
        if (applied) {
            CombatLog.register(target.getName() + " recebeu DES+10, INT+10 e FOR+10");
        }
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        if (actor == target)
            CombatLog.register(actor.getName() + " Abençoou a si mesmo com um bônus de DES, INT e FOR!");
        else
            CombatLog.register(actor.getName() + " Abençoou " + target.getName() + " com um bônus de DES, INT e FOR!");

    }
}
