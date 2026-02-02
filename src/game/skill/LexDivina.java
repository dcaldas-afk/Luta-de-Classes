package game.skill;

import java.util.Map;

import game.buffs.SilenceEffect;
import game.combat.CombatLog;
import game.combat.TargetType;
import game.core.Player;

public class LexDivina extends Skill {

    public LexDivina() {
        this.name = "Lex Divina";
        this.cost = 17;
        this.targetType = TargetType.ENEMY_SINGLE;
    }

    @Override
    public void use(Player actor, Player target) {
        if (!canUse(actor))
            return;
        consume(actor);
        CombatLog.register(actor.getName() + " usou Lex Divina em " + target.getName());
        target.addEffect(new SilenceEffect(4));
    }

    @Override
    public void displayMessage(Player actor, Player target) {
        CombatLog.register(actor.getName() + " usou " + name + "!");
    }
}
