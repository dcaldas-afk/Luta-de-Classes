package game.buffs;

import game.combat.CombatLog;
import game.core.Player;

public class WindwalkEffect implements Effect {

    private int duration = 3;

    @Override
    public String getId() {
        return "WINDWALK";
    }

    @Override
    public void apply(Player p) {
        
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
        duration = 4;
        CombatLog.register(p.getName() + " teve a duração de Caminho do Vento renovada");
    }

    @Override
    public void onExpire(Player p) {
        CombatLog.register(p.getName() + " perdeu o bônus de taxa de acerto concedido por Caminho do Vento");
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

