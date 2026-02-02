package game.skill;

import game.buffs.*;
import game.combat.*;
import game.core.*;
import java.util.Random;

public class MeteorStorm extends Skill {

    public MeteorStorm() {
        this.name = "Chuva de Meteoros";
        this.cost = 51;
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
        int damage = 9 + actor.getStats().getIntelligence() + variation;
        damage = Math.max(0, damage);

        double hit = 10;
        if (actor.hasEffect("WINDWALK"))
            hit += 20;
        hit = Math.max(0, Math.min(hit, 95));

        int roll = r.nextInt(100) - 1;

        if (actor.hasEffect("BLIND"))
            hit *= 0.1;

        if (roll < hit) {
            target.addEffect(new StunEffect(2));
            CombatLog.register(target.getName() + " foi atordoado, sendo impedido de realizar ações até o final do próximo turno");
        }

        CombatLog.register(target.getName() + " recebeu " + damage + " pontos de dano" );
        target.receiveDamage(damage);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        CombatLog.register(actor.getName() + " conjurou uma chuva de meteoros, causando dano em área a seus inimigos");
    }
}