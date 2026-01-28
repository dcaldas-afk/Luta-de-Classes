package game.buffs;

import java.util.Map;
import game.core.*;

public class StatEffect implements Effect {

    private final Map<StatType, Integer> bonuses;
    private int duration;

    public StatEffect(Map<StatType, Integer> bonuses, int duration) {
        this.bonuses = bonuses;
        this.duration = duration;
    }

    @Override
    public String getId() {
        return "StatEffect";
    }

    @Override
    public void apply(Player p) {
        bonuses.forEach((stat, value) -> {
            switch (stat) {
                case AGILITY -> p.getStats().addAgility(value);
                case STRENGTH -> p.getStats().addStrength(value);
                case VITALITY -> p.getStats().addVitality(value);
                case INTELLIGENCE -> p.getStats().addIntelligence(value);
                case DEXTERITY -> p.getStats().addDexterity(value);
                case LUCK -> p.getStats().addLuck(value);
            }
        });
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
        bonuses.forEach((stat, value) -> {
            switch (stat) {
                case AGILITY -> p.getStats().addAgility(-value);
                case STRENGTH -> p.getStats().addStrength(-value);
                case VITALITY -> p.getStats().addVitality(-value);
                case INTELLIGENCE -> p.getStats().addIntelligence(-value);
                case DEXTERITY -> p.getStats().addDexterity(-value);
                case LUCK -> p.getStats().addLuck(-value);
            }
        });
    }

    @Override
    public boolean isExpired() {
        return duration <= 0;
    }
}
