package game.skill;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class KyrieEleison extends Skill {

    public KyrieEleison() {
        this.name = "Kyrie Eleison";
        this.cost = 42;
        this.targetType = TargetType.ALLY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        if (!canUse(actor))
            return;

        consume(actor);

        target.addEffect(new ShieldEffect(5,4));
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        if (actor == target)
            CombatLog.register(actor.getName() + " usou Kyrie Eleison em si mesmo");
        else
            CombatLog.register(actor.getName() + " usou Kyrie Eleison em " + target.getName());

    }
}
