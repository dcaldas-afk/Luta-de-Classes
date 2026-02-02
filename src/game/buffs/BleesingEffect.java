package game.buffs;

import game.combat.CombatLog;
import game.core.*;

public class BleesingEffect implements Effect {

    private int duration = 0;

    @Override
    public String getId() {
        return "BLESSING";
    }

    @Override
    public void apply(Player p) {
        p.getStats().addStrength(10);
        p.getStats().addDexterity(10);
        p.getStats().addIntelligence(10);
        duration = 3;
    }

    @Override
    public void onTurnEnd(Player p) {
        duration--;
        if (duration <= 0) {
            onExpire(p);
            p.getStats().addStrength(-10);
            p.getStats().addDexterity(-10);
            p.getStats().addIntelligence(-10);        
        }
    }

    @Override
    public void refresh(Player p, Effect newEffect) {
        this.duration = 3;
        CombatLog.register(p.getName() + " teve o efeito de Bênção reaplicado");
    }

    @Override
    public void onExpire(Player p) {
        CombatLog.register("O efeito de Benção de " + p.getName() + " expirou");
    }

    @Override
    public boolean isExpired() {
        return duration <= 0;
    }

    @Override
    public boolean rollCritical(Player actor) {
        return false;
    }
}