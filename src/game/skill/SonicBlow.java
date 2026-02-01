package game.skill;

import java.util.Map;
import java.util.Random;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class SonicBlow extends Skill {

    public SonicBlow() {
        this.name = "Lâminas Destruidoras";
        this.cost = 30;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        Random r = new Random();

        DamageFormula formula = (a, t) -> {
            int base = (int) (5 + (a.getStats().getStrength() * 1.3 - t.getStats().getVitality() * 0.70));
            double variance = 0.9 + (r.nextDouble() * 0.2);
            int finalDamage = (int) (base * variance);
            return finalDamage;
        };
        CombatLog.register(actor.getName() + " usou " + name + " em " + target.getName() + "!");
        int total = new MultiAttack(8, formula).execute(actor, target);
        consume(actor);
        CombatLog.register("As Lâminas Destruidoras de " + actor.getName() + " causaram um total de " + total + " pontos de dano a " + target.getName());
        target.receiveDamage(total);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        //vazio
    }
}