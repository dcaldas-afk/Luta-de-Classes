package game.buffs;

import game.combat.CombatLog;
import game.core.Player;

public class ToxicEffect implements Effect {

    private int duration;
    private static final double DAMAGE_PERCENT = 0.20;

    public ToxicEffect(int duration) {
        this.duration = duration;
    }

    @Override
    public String getId() {
        return "TOXIC";
    }

    @Override
    public void apply(Player p) {
        CombatLog.register(p.getName() + " foi entoxicado!");
    }

    @Override
    public void onTurnEnd(Player p) {
        if (!p.isAlive())
            return;

        int damage = (int) Math.max(1, p.getMaxHP() * DAMAGE_PERCENT);

        p.receiveDamage(damage);
        p.ifDeath();

        CombatLog.register(p.getName() + " está intoxicado e recebeu " + damage + " pontos de dano");

        duration--;

        if (duration <= 0)
            onExpire(p);
    }

    @Override
    public void onExpire(Player p) {
        CombatLog.register(p.getName() + " não está mais intoxicado");
    }

    @Override
    public boolean isExpired() {
        return duration <= 0;
    }

    
    public void refresh(ToxicEffect other) {
        this.duration = other.duration;
    }

    @Override
    public boolean rollCritical(Player actor) {
        return false;
    }
}
