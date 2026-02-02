package game.skill;

import java.util.Map;
import java.util.Random;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class VenomSplasher extends Skill {

    public VenomSplasher() {
        this.name = "Explosão Tóxica";
        this.cost = 25;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        int damage = 5 + actor.getStats().getDexterity();
        damage = Math.min(50, damage);

        consume(actor);

        CombatLog.register(actor.getName() + " usou Explosão Tóxica em " + target.getName() + ", causando " + damage + " pontos de dano" );
        target.addEffect(new ToxicEffect(2));
        target.receiveDamage(damage);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        //vazio
    }
}