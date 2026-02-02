package game.skill;

import java.util.Random;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class HeavensDrive extends Skill {

    private static final int INSTANT_KILL = -9999;
    int ikFlag = 0;

    public HeavensDrive() {
        this.name = "Fúria da Terra";
        this.cost = 80;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        Random r = new Random();

        DamageFormula formula = (a, t) -> {
            int roll = r.nextInt(100);
            if (roll > 89) {
                ikFlag = 1;
                return INSTANT_KILL;
            }

            int base = (int) (5 + (a.getStats().getIntelligence() * 1.1 - t.getStats().getVitality()*0.8));
            return Math.max(0, base);
        };

        CombatLog.register(actor.getName() + " usou " + name + " em " + target.getName() + "!");
        int total = new MultiAttack(5, formula).execute(actor, target);
        if (!target.isAlive() && ikFlag == 1) {
            consume(actor);
            return;
        }
        consume(actor);
        CombatLog.register("A Fúria da Terra de " + actor.getName() + " causou um total de " + total + " pontos de dano a " + target.getName());
        target.receiveDamage(total);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        //vazio
    }
}