package game.skill;

import java.util.Map;
import java.util.Random;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class Sharpshooting extends Skill {

    public Sharpshooting() {
        this.name = "Tiro Preciso";
        this.cost = 50;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        if (target.hasEffect("PNEUMA")) {
            CombatLog.register("A nuvem de Pneuma protegeu " + target.getName() + " do " + name + " de " + actor.getName());
            return;
        }
        boolean isCritical = false;
        int damage = 0;
        int luck = actor.getStats().getLuck();
        double critChance = 0.5 + (luck * 0.01);
        damage =  (int) (actor.getStats().getDexterity()*1.5 + target.getStats().getVitality()*0.5);
        damage = Math.max(0, damage);

        consume(actor);

        if (Math.random() < critChance)
            isCritical = true;
        if (!isCritical) {
            CombatLog.register(actor.getName() + " deu um " + name + " em " + target.getName() + ", causando " + damage + " pontos de dano" );
        }else{
            damage *= 1.5;
            CombatLog.register(actor.getName() + " deu um " + name + " em " + target.getName() + ", causando " + damage + " pontos de dano [CRÍTICO]");}
        
        target.receiveDamage(damage);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        //vazio
    }
}