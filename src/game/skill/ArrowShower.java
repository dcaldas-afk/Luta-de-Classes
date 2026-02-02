package game.skill;

import game.buffs.*;
import game.combat.*;
import game.core.*;
import java.util.Random;

public class ArrowShower extends Skill {

    public ArrowShower() {
        this.name = "Chuva de Flechas";
        this.cost = 25;
        this.targetType = TargetType.ENEMY_AREA;
    }

    @Override
    public void use(Player actor, Player target) {
        if (target.hasEffect("PNEUMA")) {
            CombatLog.register("A nuvem de Pneuma protegeu " + target.getName() + " da " + name + " de " + actor.getName());
            return;
        }
        
        Random r = new Random();
        int variation = r.nextInt(15) - 8;
        int damage = (int) (5 + actor.getStats().getDexterity() - variation - target.getStats().getVitality()*0.5);
        damage = Math.max(0, damage);

        double hit = 60;
        if (actor.hasEffect("WINDWALK"))
            hit += 20;
        hit = Math.max(0, Math.min(hit, 95));

        int roll = r.nextInt(100) - 1;

        if (actor.hasEffect("BLIND"))
            hit *= 0.1;

        if (roll > hit) {
            CombatLog.register(target.getName() + " escapou da " + name);
            return;
        }

        CombatLog.register(target.getName() + " recebeu " + damage + " pontos de dano" );
        target.receiveDamage(damage);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        CombatLog.register(actor.getName() + " atirou uma" + name + ", causando dano em área a seus inimigos");
    }
}