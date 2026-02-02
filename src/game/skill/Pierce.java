package game.skill;

import game.combat.*;
import game.core.*;
import java.util.Random;

public class Pierce extends Skill {

    public Pierce() {
        this.name = "Perfurar";
        this.cost = 15;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
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
        int damage = (int) (10 + actor.getStats().getStrength() + target.getStats().getVitality()*0.5);
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