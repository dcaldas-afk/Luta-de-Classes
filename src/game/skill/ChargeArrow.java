package game.skill;

import java.util.Map;
import java.util.Random;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class ChargeArrow extends Skill {

    public ChargeArrow() {
        this.name = "Disparo Violento";
        this.cost = 15;
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
        damage =  (int) (10 + actor.getStats().getDexterity() - target.getStats().getVitality()*0.8);
        damage = Math.max(0, damage);
        
        double hit = 60;
        if (actor.hasEffect("WINDWALK"))
            hit += 20;
        hit = Math.max(0, Math.min(hit, 95));

        Random r = new Random();
        int roll = r.nextInt(100) - 1;

        if (actor.hasEffect("BLIND"))
            hit *= 0.1;
        
        consume(actor);
        
        if (roll > hit) {
            CombatLog.register(target.getName() + " escapou do " + name + " de " + actor.getName());
            return;
        }

        if (Math.random() < critChance)
            isCritical = true;
        if (!isCritical) {
            CombatLog.register(actor.getName() + " deu um " + name + " em " + target.getName() + ", causando " + damage + " pontos de dano" );
        }else{
            damage *= 1.5;
            CombatLog.register(actor.getName() + " deu um " + name + " em " + target.getName() + ", causando " + damage + " pontos de dano [CRÍTICO]");}
        CombatLog.register(target.getName() + " foi atordoado pelo " + name + " de " + actor.getName());
        target.addEffect(new StunEffect(2));
        target.receiveDamage(damage);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        //vazio
    }
}