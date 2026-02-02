package game.buffs;

import game.combat.CombatLog;
import game.core.Player;

public class PoisonEffect implements Effect {

    private int duration;
    private static final double DAMAGE_PERCENT = 0.10;

    public PoisonEffect(int duration) {
        this.duration = duration;
    }

    @Override
    public String getId() {
        return "POISON";
    }

    @Override
    public void apply(Player p) {
        CombatLog.register(p.getName() + " foi envenenado e irá receber um dano fixo por " + duration + " turnos!");
    }

    @Override
    public void onTurnEnd(Player p) {
        if (!p.isAlive())
            return;

        int damage = (int) Math.max(1, p.getMaxHP() * DAMAGE_PERCENT);

        p.receiveDamage(damage);
        p.ifDeath();

        CombatLog.register(p.getName() + " está envenenado e recebeu " + damage + " pontos de dano");

        duration--;

        if (duration <= 0)
            onExpire(p);
    }

    @Override
    public void onExpire(Player p) {
        CombatLog.register(p.getName() + " não está mais envenenado");
    }

    @Override
    public boolean isExpired() {
        return duration <= 0;
    }

    
    public void refresh(PoisonEffect other) {
        this.duration = other.duration;
    }

    @Override
    public boolean rollCritical(Player actor) {
        return false;
    }
}
