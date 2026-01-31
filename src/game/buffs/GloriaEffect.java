package game.buffs;

import java.util.Map;

import game.combat.CombatLog;
import game.core.*;

public class GloriaEffect implements Effect {

    private int duration = 2;

    @Override
    public String getId() {
        return "GLORIA";
    }

    @Override
    public void apply(Player p) {
        p.addEffect(new StatEffect(Map.of(StatType.LUCK, 30), duration));
    }

    @Override
    public void onTurnEnd(Player p) {
        duration--;
        if (duration <= 0) {
            onExpire(p);
        }
    }

    @Override
    public void refresh(Player p, Effect newEffect) {
        if (newEffect instanceof GloriaEffect s) {
            this.duration = s.duration;
            CombatLog.register(p.getName() + " teve o efeito de Gloria reaplicado");
        }
    }

    @Override
    public void onExpire(Player p) {
        CombatLog.register("O efeito de Gloria expirou. A sorte de " + p.getName() + " voltou ao normal");
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