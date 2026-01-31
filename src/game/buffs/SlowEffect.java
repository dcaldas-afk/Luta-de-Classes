package game.buffs;

import java.util.Map;

import game.combat.CombatLog;
import game.core.*;

public class SlowEffect implements Effect {

    private int duration;
    private boolean expired = false;

    public SlowEffect(int duration) {
        this.duration = duration;
    }

    @Override
    public String getId() {
        return "SLOW";
    }

    @Override
    public void apply(Player p) {
        p.addEffect(new StatEffect(Map.of(StatType.AGILITY, -5), duration));
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
        if (newEffect instanceof SlowEffect s) {
            this.duration = s.duration;
            CombatLog.register(p.getName() + " teve o efeito de Lentidão reaplicado");
        }
    }

    @Override
    public void onExpire(Player p) {
        CombatLog.register("O efeito de lentidão em " + p.getName() + " expirou");
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