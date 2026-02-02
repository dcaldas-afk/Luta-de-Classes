package game.skill;

import java.util.Map;
import java.util.Random;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class JupitelThunder extends Skill {

    public JupitelThunder() {
        this.name = "Trovão de Júpiter";
        this.cost = 80;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        if (target.hasEffect("PNEUMA")) {
            CombatLog.register("A nuvem de Pneuma protegeu " + target.getName() + " do " + name + " de " + actor.getName());
            return;
        }
        Random rand = new Random();
        int variation = rand.nextInt(20) - 6;
        int damage = (int) (15 + actor.getStats().getIntelligence()*1.2 + variation);
        damage = Math.max(0, damage);

        consume(actor);

        CombatLog.register(actor.getName() + " conjurou um Trovão de Júpiter sobre " + target.getName() + ", causando " + damage + " pontos de dano");
        int roll = rand.nextInt(100);
        if (roll > 20) {
            target.addEffect(new BlindEffect(3));
            CombatLog.register(target.getName() + " foi cegado pela luz do Trovão de Júpiter de " + actor.getName() + ", tendo sua taxa de acerto afetada por 2 turnos");
        }
        roll = rand.nextInt(100);
        if (roll > 40) {
            target.addEffect(new StunEffect(2));
            CombatLog.register(target.getName() + " foi atordoado pelo Trovão de Júpiter de " + actor.getName() + ", tendo sua taxa de acerto afetada por 2 turnos");
        }
        target.receiveDamage(damage);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
       //vazio
    }
}