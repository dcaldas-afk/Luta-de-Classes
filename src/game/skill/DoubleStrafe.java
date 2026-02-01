package game.skill;

import java.util.Map;
import java.util.Random;

import game.buffs.*;
import game.combat.*;
import game.core.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DoubleStrafe extends Skill {

    public DoubleStrafe() {
        this.name = "Rajada de Flechas";
        this.cost = 15;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {

        Random r = new Random();

        DamageFormula formula = (a, t) -> {
            int base = 10 + ((int) (a.getStats().getDexterity() - t.getStats().getVitality()*0.8));
            double variance = 0.9 + (r.nextDouble() * 0.2);
            int finalDamage = (int) (base * variance);
            return finalDamage;
        };
        CombatLog.register(actor.getName() + " usou " + name + " em " + target.getName() + "!");
        int total = new MultiAttack(2, formula).execute(actor, target);
        CombatLog.register("A Rajada de Flechas de " + actor.getName() + " causou um total de " + total + " pontos de dano a " + target.getName());
        target.receiveDamage(total);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        //vazio
    }
}