package game.buffs;

import game.combat.CombatLog;
import game.core.Player;
import game.resources.*;

public class SuffragiumEffect implements Effect {

    private int duration = 6;
    private int mpBonus;

    @Override
    public String getId() {
        return "SUFFRAGIUM";
    }

    @Override
    public void apply(Player p) {
        Mana mana = p.getResource(Mana.class);

        if (mana == null)
            return;

        mpBonus = (int) (mana.getMax() * 0.5);

        mana.increaseMaxMP(mpBonus);
        mana.increaseCurrentMP(mpBonus);
        p.getStats().addAgility(5);
        p.getStats().addIntelligence(5);
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
        duration = 6;
        CombatLog.register(p.getName() + " teve a duração de Suffragium renovada");
    }

    @Override
    public void onExpire(Player p) {
        Mana mana = p.getResource(Mana.class);

        if (mana == null)
            return;
            
        mana.decreaseMaxMP(mpBonus);
        p.getStats().addIntelligence(-5);
        p.getStats().addIntelligence(-5);

        if (mana.getCurrent() > mana.getMax()) {
            mana.setCurrentMP(mana.getCurrent());
        }
        CombatLog.register("O efeito de Suffragium passou. " + p.getName() + " perdeu o bônus de AGI e INT, e seu MP máximo voltou ao normal");
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

