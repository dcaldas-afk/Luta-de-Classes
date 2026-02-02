package game.buffs;

import java.util.Map;

import game.combat.CombatLog;
import game.core.*;

public class PneumaEffect implements Effect {

    private int duration = 0;

    @Override
    public String getId() {
        return "PNEUMA";
    }

    @Override
    public void apply(Player p) {
        duration = 4;
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
        if (newEffect instanceof PneumaEffect s) {
            this.duration = 2;
            CombatLog.register(p.getName() + " continua protegido por Pneuma por mais 4 turnos");
        }
    }

    @Override
    public void onExpire(Player p) {
        CombatLog.register( "A densa nuvem criada a partir de Pneuma se dissipou, deixando " + p.getName() + " vulnerável novamente");
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