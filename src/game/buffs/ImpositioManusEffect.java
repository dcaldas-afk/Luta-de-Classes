package game.buffs;

import game.combat.CombatLog;
import game.core.Player;

public class ImpositioManusEffect implements Effect {

    private int duration = 3;

    @Override
    public String getId() {
        return "MANUS";
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
        duration = 3;
        CombatLog.register(p.getName() + " teve a duração de Impositio Manus renovada");
    }

    @Override
    public void onExpire(Player p) {
        CombatLog.register(p.getName() + " perdeu bônus de ataque garantido por Impositio Manus");
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

