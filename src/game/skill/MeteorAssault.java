package game.skill;

import java.util.Map;
import java.util.Random;

import game.buffs.*;
import game.combat.*;
import game.core.*;

public class MeteorAssault extends Skill {

    public MeteorAssault() {
        this.name = "Impacto Meteoro";
        this.cost = 30;
        this.targetType = TargetType.ENEMY_AREA;
    }

    @Override
    public void use(Player actor, Player target) {
        Random rand = new Random();
        int variation = rand.nextInt(30) - 15;
        int damage = (int) (10 + actor.getStats().getStrength() + actor.getStats().getDexterity()*0.3 + variation - target.getStats().getVitality());
        damage = Math.max(0, damage);

        //consume(actor);

        CombatLog.register(target.getName() + " recebeu " + damage + " pontos de dano");
        target.receiveDamage(damage);
        target.ifDeath();
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        CombatLog.register(actor.getName() + " usou Impacto Meteoro, afetando toda a equipe inimiga! ");
    }
}