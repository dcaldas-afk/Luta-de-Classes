package game.buffs;

import game.combat.CombatLog;
import game.core.Player;

public class AngelusEffect implements Effect {

    private int duration = 3;
    private int hpBonus;

    @Override
    public String getId() {
        return "ANGELUS";
    }

    @Override
    public void apply(Player p) {
        hpBonus = (int) (p.getMaxHP() * 0.5);

        p.increaseMaxHP(hpBonus);
        p.increaseCurrentHP(hpBonus);
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
        CombatLog.register(p.getName() + " teve a duração de Angelus renovada");
    }

    @Override
    public void onExpire(Player p) {
        p.decreaseMaxHP(hpBonus);

        if (p.getCurrentHP() > p.getMaxHP()) {
            p.setCurrentHP(p.getMaxHP());
        }
        CombatLog.register("O efeito de Angelus passou. " + p.getName() + " perdeu o buff de Vitalidade e seu HP voltou ao normal");
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

