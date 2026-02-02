package game.buffs;

import game.combat.CombatLog;
import game.core.*;

public class ConcentrationEffect implements Effect {

    private int duration = 0;
    int dex = 0;

    @Override
    public String getId() {
        return "CONCENTRATION";
    }

    @Override
    public void apply(Player p) {
        dex = p.getStats().getDexterity()*2;
        p.getStats().addDexterity(dex);
        duration = 2;
    }

    @Override
    public void onTurnEnd(Player p) {
        duration--;
        if (duration <= 0) {
            onExpire(p);
            p.getStats().addDexterity(-dex);
            dex = 0; 
        }
    }

    @Override
    public void refresh(Player p, Effect newEffect) {
        this.duration = 2;
        CombatLog.register(p.getName() + " teve o efeito de Concentração reaplicado");
    }

    @Override
    public void onExpire(Player p) {
        CombatLog.register("O efeito de Concentração de " + p.getName() + " expirou");
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