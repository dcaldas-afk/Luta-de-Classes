package game.buffs;

import game.combat.*;
import game.core.Player;

import java.util.Random;

public class MultiAttack implements Effect {

    private static final int INSTANT_KILL = -9999;
    private final int hits;
    private final DamageFormula formula;
    private boolean expired = false;

    public MultiAttack(int hits, DamageFormula formula) {
        this.hits = hits;
        this.formula = formula;
    }

    @Override
    public String getId() {
        return "MULTI_HIT";
    }

    @Override
    public void apply(Player target) {
        //O efeito é instantâneo
    }

    @Override
    public void onTurnEnd(Player target) {
        expired = true;
    }

    @Override
    public void onExpire(Player target) {
        // nada
    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    public int execute(Player actor, Player target) {
        int totalDamage = 0;

        for (int i = 1; i <= hits; i++) {

            int damage = formula.calculate(actor, target);

            // MORTE INSTANTÂNEA
            if (damage == INSTANT_KILL) {
                CombatLog.register(
                    target.getName() + " foi petrificado, morrendo instantaneamente!"
                );
                target.receiveDamage(target.getCurrentHP());
                return totalDamage;
            }

            boolean isCritical = rollCritical(actor);

            if (isCritical) {
                damage *= 1.5;
                CombatLog.register(target.getName() + " recebeu " + damage + " pontos de dano [CRÍTICO]");
            } else {
                Random r = new Random();
                int baseAccuracy = 50;
                if (actor.hasEffect("WINDWALK"))
                    baseAccuracy += 20;
                int dex = actor.getStats().getDexterity();
                int targetEvasion = target.getStats().getAgility();
                int hit = baseAccuracy + dex * 2 - targetEvasion * 2;
                hit = Math.max(0, Math.min(hit, 95));
                int roll = r.nextInt(100);
                if (actor.hasEffect("BLIND"))
                    hit *= 0.1;

                if (roll > hit) {
                    CombatLog.register(target.getName() + " desviou do ataque de " + actor.getName());
                    damage = 0;
                } else {
                    CombatLog.register(target.getName() + " recebeu " + damage + " pontos de dano");
                }
            }
            totalDamage += damage;
        }
        return totalDamage;
    }

    @Override
    public boolean rollCritical(Player actor) {
        int luck = actor.getStats().getLuck();
        double critChance = 0.01 + (luck * 0.01);
        return Math.random() < critChance;
    }
}
