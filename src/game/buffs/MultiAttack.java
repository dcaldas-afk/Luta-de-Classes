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
                CombatLog.register(target.getName() + " recebeu " + damage + " pontos de dano");
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
        double critChance = 0.20 + (luck * 0.01);
        return Math.random() < critChance;
    }
}
