package game.skill;

import java.util.Map;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class ImpositioManus extends Skill {

    public ImpositioManus() {
        this.name = "Impositio Manus";
        this.cost = 63;
        this.targetType = TargetType.ENEMY_AREA;
    }

    @Override
    public void use(Player actor, Player target) {
        boolean applied = target.addEffect(new ImpositioManusEffect());
        if (applied) {
            CombatLog.register(target.getName() + " receberá +20% de dano por 3 turnos!");
        }
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        CombatLog.register(actor.getName() + " usou " + name + ", afetando todos os inimigos em campo!");
    }
}
