package game.skill;

import java.util.List;

import game.buffs.StatEffect;
import game.combat.CombatLog;
import game.combat.TargetType;
import game.core.Player;
import game.resources.Mana;

// Regenerar mana
public class Magnificat extends Skill {
    public Magnificat () {
        this.name = "Magnificat";
        this.cost = 10;
        this.targetType = targetType.ALLY_AREA;
    }

    @Override
    public void use(Player caster, Player target) {
        int value = 20 + target.getLevel();
        target.getResource(Mana.class).regenerate(value);
        CombatLog.register(target.getName() + " recuperou " + value + "pontos de mana");
    }

    @Override
    public void displayMessage(Player caster, Player target) {
        CombatLog.register(target.getName() + " usou " + name + " recuperando parte do MP de sua equipe");
    }
}