package game.skill;

import java.util.Random;

import game.combat.*;
import game.core.*;

public class SightTrasher extends Skill {

    public SightTrasher() {
        this.name = "Supernova";
        this.cost = 32;
        this.targetType = TargetType.ENEMY_AREA;
    }

    @Override
    public void use(Player actor, Player target) {
        Random rand = new Random();
        if (!canUse(actor)) {
            CombatLog.register(actor.getName() + " tentou conjurar Supernova, mas não tinha mana!");
            return;
        }

        int variation = rand.nextInt(10) - 5;
        int damage = 2 + actor.getStats().getIntelligence() + variation;
        damage = Math.max(0, damage);

        CombatLog.register(target.getName() + " recebeu " + damage + " pontos de dano" );
        target.receiveDamage(damage);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        CombatLog.register(actor.getName() + " conjurou Supernova, deixando o campo em chamas!");
    }
}
