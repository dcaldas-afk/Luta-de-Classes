package game.skill;

import java.util.Random;

import game.combat.CombatLog;
import game.combat.TargetType;
import game.core.Player;

public class Fireball extends Skill {

    public Fireball() {
        this.name = "Bola de Fogo";
        this.cost = 20;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        Random rand = new Random();
        if (!canUse(actor)) {
            CombatLog.register(actor.getName() + " tentou conjurar Bola de Fogo, mas não tinha mana!");
            return;
        }

        consume(actor);

        //colocar dano aleatório também depois
        int variation = rand.nextInt(10) - 3;
        int damage = 30 + actor.getStats().getIntelligence() + variation;
        damage = Math.max(0, damage);

        CombatLog.register(actor.getName() + " lançou Bola de Fogo em " + target.getName() + ", causando " + damage + " pontos de dano" );
        target.receiveDamage(damage);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player caster, Player target) {
    }
}
