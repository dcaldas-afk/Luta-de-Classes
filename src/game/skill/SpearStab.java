package game.skill;

import java.util.Random;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class SpearStab extends Skill {

    public SpearStab() {
        this.name = "Estocada";
        this.cost = 20;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {

        if (target.hasEffect("PNEUMA")) {
            CombatLog.register(actor.getName() + " tentou usar " + name + " em " + target.getName() + ", que está sob o efeito de Pneuma e preveniu o ataque");
            return;
        }
        
        Random r = new Random();

        int baseAccuracy = 67;
        if (actor.hasEffect("WINDWALK"))
            baseAccuracy += 20;
        int dex = actor.getStats().getDexterity();
        int targetEvasion = target.getStats().getAgility();
        int hit = baseAccuracy + dex*2 - targetEvasion*2;
        hit = Math.max(0, Math.min(hit, 100));

        int roll = r.nextInt(100) - 1;

        if (actor.hasEffect("BLIND"))
            hit *= 0.1;
        if (roll > hit) {
            CombatLog.register(target.getName() + " desviou do ataque de " + actor.getName());
            return;
        }

        int variation = r.nextInt(15) - 7;
        int damage = (int) (10 + actor.getStats().getStrength() + variation + target.getStats().getVitality()*0.8);
        damage = Math.max(0, damage);

        consume(actor);

        CombatLog.register(actor.getName() + " usou " + name + " em " + target.getName() + ", causando " + damage + " pontos de dano" );
        target.receiveDamage(damage);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
    }
}