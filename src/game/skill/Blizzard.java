package game.skill;

import java.util.Map;
import java.util.Random;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class Blizzard extends Skill {

    public Blizzard() {
        this.name = "Nevasca";
        this.cost = 30;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        Random rand = new Random();
        int variation = rand.nextInt(15) - 6;
        int damage = 33 + actor.getStats().getIntelligence() + variation;
        damage = Math.max(0, damage);

        consume(actor);

        CombatLog.register(actor.getName() + " conjurou uma nevasca sobre " + target.getName() + ", causando " + damage + " pontos de dano" );
        target.addEffect(new SlowEffect(3));
        CombatLog.register(target.getName() + " foi congelado, sofrendo lentidão e recebendo AGI-5");
        target.receiveDamage(damage);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        CombatLog.register(actor.getName() + " conjurou uma " + name);
    }
}