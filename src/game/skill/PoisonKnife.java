package game.skill;

import java.util.Map;
import java.util.Random;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class PoisonKnife extends Skill {

    public PoisonKnife() {
        this.name = "Faca Envenenada";
        this.cost = 4;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        if (target.hasEffect("PNEUMA")) {
            CombatLog.register("A nuvem de Pneuma protegeu " + target.getName() + " da " + name + " de " + actor.getName());
            return;
        }
        int damage = 5 + actor.getStats().getDexterity();
        damage = Math.min(20, damage);

        consume(actor);

        CombatLog.register(actor.getName() + " atirou uma Faca Envenenada em " + target.getName() + ", causando " + damage + " pontos de dano" );
        target.addEffect(new PoisonEffect(3));
        target.receiveDamage(damage);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        //vazio
    }
}