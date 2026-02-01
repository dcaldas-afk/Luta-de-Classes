package game.skill;

import java.util.Map;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class Gloria extends Skill {

    public Gloria() {
        this.name = "Gloria";
        this.cost = 20;
        this.targetType = TargetType.ALLY_AREA;
    }

    @Override
    public void use(Player actor, Player target) {
        if (!canUse(actor))
            return;
        boolean applied = target.addEffect(new GloriaEffect());
        if (applied) {
            CombatLog.register(target.getName() + " recebeu SOR+30 por 2 turnos!");
        }
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        CombatLog.register(actor.getName() + " usou " + name + " fortalecendo toda a sua equipe!");
    }
}
