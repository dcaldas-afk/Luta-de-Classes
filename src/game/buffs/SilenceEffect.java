package game.buffs;

import game.combat.CombatLog;
import game.core.Player;

public class SilenceEffect implements Effect {

    private int duration;
    private boolean expired = false;

    public SilenceEffect(int duration) {
        this.duration = duration;
    }

    @Override
    public String getId() {
        return "SILENCE";
    }

    @Override
    public void apply(Player p) {
        CombatLog.register(p.getName() + " foi silenciado, ficando incapaz de usar habilidades e magias");
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
        if (newEffect instanceof SilenceEffect s) {
            this.duration = s.duration;
            CombatLog.register(p.getName() + " teve o efeito de Lex Divina reaplicado");
        }
    }

    @Override
    public void onExpire(Player p) {
        CombatLog.register(p.getName() + " pode usar habilidades novamente");
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