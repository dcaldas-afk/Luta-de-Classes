package game.buffs;

import game.combat.CombatLog;
import game.core.*;

public class BlindEffect implements Effect {

    private int duration;
    private boolean expired = false;

    public BlindEffect(int duration) {
        this.duration = duration;
    }

    @Override
    public String getId() {
        return "BLIND";
    }

    @Override
    public void apply(Player p) {
        // vazio
    }

    @Override
    public void onTurnEnd(Player p) {
        duration--;
        if (duration <= 0) {
            expired = true;
            onExpire(p);
        }
    }

    @Override
    public void refresh(Player p, Effect newEffect) {
        if (newEffect instanceof BlindEffect s) {
            this.duration = s.duration;
            CombatLog.register(p.getName() + " teve o efeito de cegueira reaplicado");
        }
    }

    @Override
    public void onExpire(Player p) {
        CombatLog.register(p.getName() + " voltou a enxergar normalmente");
    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    @Override
    public boolean rollCritical(Player actor) {
        return false;
    }
}