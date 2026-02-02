package game.skill;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class Concentration extends Skill {

    public Concentration() {
        this.name = "Concentrar";
        this.cost = 18;
        this.targetType = TargetType.SELF;
    }

    @Override
    public void use(Player actor, Player target) {

    boolean applied = target.addEffect(new ConcentrationEffect());

    consume(actor);

        if (applied) {
            CombatLog.register(actor.getName() + " se concentrou profundamente e recebeu DES+100% até o final do próximo turno");
        }
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        //vazio
    }
}
