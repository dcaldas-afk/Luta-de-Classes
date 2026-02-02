package game.buffs;

import game.combat.CombatLog;
import game.core.*;

public class StunEffect implements Effect {

    private int duration;
    private boolean expired = false;

    public StunEffect(int duration) {
        this.duration = duration;
    }

    @Override
    public String getId() {
        return "STUN";
    }

    @Override
    public void apply(Player p) {
        //vazio
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
        if (newEffect instanceof StunEffect s) {
            this.duration = s.duration;
            CombatLog.register(p.getName() + " teve o efeito de atordoamento reaplicado");
        }
    }

    @Override
    public void onExpire(Player p) {
        CombatLog.register(p.getName() + " não está mais atordoado e pode realizar ações novamente");
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