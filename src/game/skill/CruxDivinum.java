package game.skill;

import java.util.Map;
import java.util.Random;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class CruxDivinum extends Skill {

    public CruxDivinum() {
        this.name = "Crux Divinum";
        this.cost = 26;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {

        Random r = new Random();

        DamageFormula formula = (a, t) -> {
            int base = 10 + ((int) (a.getStats().getStrength() - t.getStats().getVitality()*0.8));
            double variance = 0.9 + (r.nextDouble() * 0.2);
            int finalDamage = (int) (base * variance);
            return finalDamage;
        };
        CombatLog.register(actor.getName() + " usou " + name + " em " + target.getName() + "!");
        int total = new MultiAttack(2, formula).execute(actor, target);
        target.addEffect(new BlindEffect(3));
        CombatLog.register("A luz produzida por Crux Divinum cegou " + actor.getName() + " por 3 turnos");
        consume(actor);
        CombatLog.register("Crux Divinum " + " causou um total de " + total + " pontos de dano a " + target.getName());
        target.receiveDamage(total);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        //vazio
    }
}