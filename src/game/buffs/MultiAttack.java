package game.buffs;

import game.combat.*;
import game.core.Player;

import java.util.Random;

public class MultiAttack implements Effect {

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
            boolean isCritical = rollCritical(actor);
            int damage = Math.max(0,formula.calculate(actor, target));
            if (!isCritical) {
                // balancear isso depois
                Random r = new Random();
                int baseAccuracy = 50;
                int dex = actor.getStats().getDexterity();
                int targetEvasion = target.getStats().getAgility();
                int hit = baseAccuracy + dex*2 - targetEvasion*2;
                hit = Math.max(0, Math.min(hit, 95));

                int roll = r.nextInt(100) - 1;

                if (roll > hit) {
                    CombatLog.register(target.getName() + " desviou do ataque de " + actor.getName());
                    damage = 0;
                }else{
                    CombatLog.register(target.getName() + " recebeu " + damage + " pontos de dano");}
            }else{
                damage *= 1.5;
                CombatLog.register(target.getName() + " recebeu " + damage + " pontos de dano [CRÍTICO]");}
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
