package game.skill;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class Pneuma extends Skill {

    public Pneuma() {
        this.name = "Pneuma";
        this.cost = 20;
        this.targetType = TargetType.ALLY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        if (!canUse(actor))
            return;

        consume(actor);

        target.addEffect(new PneumaEffect());
        if (actor == target) {
            CombatLog.register(actor.getName() + " usou Pneuma, soprando uma nuvem espessa, capaz de proteger a si mesmo de ataques à distância por 4 turnos");
        }else{
            CombatLog.register(actor.getName() + " usou Pneuma, soprando uma nuvem espessa, capaz de proteger " + target.getName() + " de ataques à distância por 4 turnos");
        }

    }

    @Override
    public void displayMessage(Player actor, Player target) {
        // vazio
    }
}
