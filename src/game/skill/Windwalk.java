package game.skill;

import java.util.Map;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class Windwalk extends Skill {

    public Windwalk() {
        this.name = "Caminho do Vento";
        this.cost = 60;
        this.targetType = TargetType.ALLY_AREA;
    }

    @Override
    public void use(Player actor, Player target) {
        boolean applied = target.addEffect(new WindwalkEffect());
        if (applied) {
            CombatLog.register(target.getName() + " receberá HIT+20% de dano por 3 turnos!");
        }
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        CombatLog.register(actor.getName() + " usou " + name + ", afetando todos os seus aliados!");
    }
}
