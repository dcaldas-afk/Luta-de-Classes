package game.buffs;

import game.combat.CombatLog;
import game.core.Player;

public class ShieldEffect implements Effect {

    private int shields;
    private int duration;

    public ShieldEffect(int shields, int duration) {
        this.shields = shields;
        this.duration = duration;
    }

    @Override
    public String getId() {
        return "SHIELD";
    }

    @Override
    public void apply(Player p) {
        CombatLog.register(p.getName() + " recebeu a proteção de " + shields + " escudos através de Kyrie Eleison!");
    }

    public boolean block(Player p) {
        if (shields <= 0)
            return false;

        shields--;

        CombatLog.register(p.getName() + " bloqueou um ataque! (" + shields + " escudos restantes)");

        if (shields == 0) {
            CombatLog.register(p.getName() + " perdeu todos os escudos!");
        }

        return true;
    }

    @Override
    public void onTurnEnd(Player p) {
        duration--;

        if (duration <= 0)
            onExpire(p);
    }

    @Override
    public void onExpire(Player p) {
        CombatLog.register(p.getName() + " perdeu a proteção de Kyrie Eleison");
    }

    @Override
    public boolean isExpired() {
        return duration <= 0 || shields <= 0;
    }

    @Override
    public void refresh(Player p, Effect newEffect) {
        duration = 4;
        CombatLog.register(p.getName() + " teve o efeito de Kyrie Eleison renovado");
    }

    @Override
    public boolean rollCritical(Player actor) {
        return false;
    }
}
