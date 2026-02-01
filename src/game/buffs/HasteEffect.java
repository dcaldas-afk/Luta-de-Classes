package game.buffs;

import java.util.Map;

import game.combat.CombatLog;
import game.core.*;

public class HasteEffect implements Effect {

    private int duration = 0;

    @Override
    public String getId() {
        return "HASTE";
    }

    @Override
    public void apply(Player p) {
        p.getStats().addAgility(15);
        duration = 6;
    }

    @Override
    public void onTurnEnd(Player p) {
        duration--;
        if (duration <= 0) {
            onExpire(p);
            p.getStats().addAgility(-15);
        }
    }

    @Override
    public void refresh(Player p, Effect newEffect) {
        if (newEffect instanceof HasteEffect s) {
            this.duration = 6;
            CombatLog.register(p.getName() + " teve o efeito de Aumentar Agilidade reaplicado");
        }
    }

    @Override
    public void onExpire(Player p) {
        CombatLog.register("O efeito de Aumentar Agilidade expirou. A agilidade de " + p.getName() + " voltou ao normal");
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