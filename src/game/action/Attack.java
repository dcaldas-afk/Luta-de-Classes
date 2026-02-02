package game.action;

import game.combat.CombatLog;
import game.combat.TargetType;
import game.core.*;
import java.util.Random;

public class Attack implements Action {

    @Override
    public String getName() {
        return "Ataque básico";
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.ENEMY_SINGLE;
    }

    @Override
    public void act(Player player, Player target) {
        if (player.getJob() == Job.ARCHER) {
            if (target.hasEffect("PNEUMA")) {
                CombatLog.register(player.getName() + " tentou atirar uma flecha em " + target.getName() + ", mas a nuvem de Pneuma o protegeu.");
                return;
            }
        }
        Random r = new Random();
        
        int baseDmg = player.getStats().getStrength();
        int variation = r.nextInt(7) - 3;

        // balancear isso depois
        int baseAccuracy = 50;
        if (player.hasEffect("WINDWALK"))
            baseAccuracy += 20;
        int dex = player.getStats().getDexterity();
        int targetEvasion = target.getStats().getAgility();
        int hit = baseAccuracy + dex*2 - targetEvasion*2;
        hit = Math.max(0, Math.min(hit, 100));

        int roll = r.nextInt(100) - 1;

        if (player.hasEffect("BLIND"))
            hit *= 0.1;
        if (roll > hit) {
            CombatLog.register(target.getName() + " desviou do ataque de " + player.getName());
            return;
        }
        
        int damage = (int) (2 + baseDmg + variation - target.getStats().getVitality()*0.5);
        damage = Math.max(0, damage);
        damage = target.receiveDamage(damage);
        CombatLog.register(player.getName() + " atacou " + target.getName() + ", causando " + damage + " pontos de dano");
        target.ifDeath();
    }
}