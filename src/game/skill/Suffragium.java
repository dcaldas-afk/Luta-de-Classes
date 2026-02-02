package game.skill;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class Suffragium extends Skill {

    public Suffragium() {
        this.name = "Suffragium";
        this.cost = 75;
        this.targetType = TargetType.ALLY_AREA;
    }

    @Override
    public void use(Player actor, Player target) {
        boolean applied = target.addEffect(new SuffragiumEffect());
        if (applied) {
            CombatLog.register(target.getName() + " recebeu +50% MP máximo, AGI+5 e INT+5 por 6 turnos");        }
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        CombatLog.register(actor.getName() + " usou " + name + ", fortalecendo toda a sua equipe");
    }
}
