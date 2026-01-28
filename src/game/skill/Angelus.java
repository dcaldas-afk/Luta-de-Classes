package game.skill;

import java.util.Map;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class Angelus extends Skill {

    public Angelus() {
        this.name = "Angelus";
        this.cost = 70;
        this.targetType = TargetType.ALLY_AREA;
    }

    @Override
    public void use(Player actor, Player target) {
        int hpBonus = (int) (target.getMaxHP() * 0.5);

        target.addEffect(new AngelusEffect());

        CombatLog.register(target.getName() + " recebeu +50% HP máximo e VIT+5 por 3 turnos!");
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        CombatLog.register(actor.getName() + " usou " + name + " fortalecendo toda a sua equipe!");
    }
}
