package game.skill;

import java.util.Map;

import game.buffs.StatEffect;
import game.combat.CombatLog;
import game.combat.TargetType;
import game.core.Player;
import game.core.StatType;

public class Haste extends Skill {

    public Haste() {
        this.name = "Aumentar Agilidade";
        this.cost = 10;
        this.targetType = TargetType.ALLY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        if (!canUse(actor))
            return;

        consume(actor);

        target.addEffect(new StatEffect(Map.of(StatType.AGILITY, 10), 5));
        CombatLog.register(target.getName() + " recebeu AGI+10 por 5 turnos");
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        CombatLog.register(actor.getName() + " usou " + name + "!");
    }
}
