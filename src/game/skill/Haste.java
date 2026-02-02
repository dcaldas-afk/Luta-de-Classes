package game.skill;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class Haste extends Skill {

    public Haste() {
        this.name = "Aumentar Agilidade";
        this.cost = 15;
        this.targetType = TargetType.ALLY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        if (!canUse(actor))
            return;

        consume(actor);

        target.addEffect(new HasteEffect());
        CombatLog.register(target.getName() + " recebeu AGI+15 por 5 turnos");
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        if (actor == target)
            CombatLog.register(actor.getName() + " usou Aumentar Agilidade em si mesmo");
        else
            CombatLog.register(actor.getName() + " usou Aumentar Agilidade em " + target.getName());

    }
}
