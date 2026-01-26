package game.skill;

import game.combat.CombatLog;
import game.combat.TargetType;
import game.core.Player;

public class Fireball extends Skill {

    public Fireball() {
        this.name = "Fireball";
        this.cost = 10;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {

        if (!canUse(actor)) {
            CombatLog.register(actor.getName() + " tentou conjurar Fireball, mas não tinha mana!");
            return;
        }

        consume(actor);

        int damage = 30 + actor.getStats().getIntelligence();
        target.receiveDamage(damage);

        CombatLog.register(
            actor.getName() + " lançou Fireball em " +
            target.getName() + ", causando " + damage + " de dano"
        );
    }
}
