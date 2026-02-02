package game.skill;

import java.util.Random;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class CruxMagnum extends Skill {

    public CruxMagnum() {
        this.name = "Crux Magnum";
        this.cost = 35;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        int damage = (int) (actor.getMaxHP()*0.3);
        CombatLog.register(actor.getName() + " sacrificou 30% do seu HP máximo para usar Crux Magnum");
        actor.receiveDamage(damage);
        if (actor.ifDeath())
            return;
        Random r = new Random();

        int baseAccuracy = 60;
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

        
        damage = Math.max(0, damage);

        //consume(actor);

        CombatLog.register(actor.getName() + " usou " + name + " em " + target.getName() + ", causando " + damage + " pontos de dano" );
        target.receiveDamage(damage);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
    }
}