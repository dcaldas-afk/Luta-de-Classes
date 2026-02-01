package game.buffs;

import game.core.Player;

public class MaxHpEffect implements Effect {

    private final int bonus;
    private int duration;

    public MaxHpEffect(int bonus, int duration) {
        this.bonus = bonus;
        this.duration = duration;
    }

    @Override
    public String getId() {
        return "MaxHPEffect";
    }

    @Override
    public void apply(Player p) {
        p.increaseMaxHP(bonus);
        p.increaseCurrentHP(bonus);
    }

    @Override
    public void onTurnEnd(Player p) {
        duration--;
        if (duration <= 0) {
            onExpire(p);
        }
    }

    @Override
    public void onExpire(Player p) {
        p.increaseMaxHP(-bonus);

        if (p.getCurrentHP() > p.getMaxHP()) {
            p.setCurrentHP(p.getMaxHP());
        }
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
