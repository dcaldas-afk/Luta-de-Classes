package game.action;

import game.combat.TargetType;
import game.core.Player;

public class SkillMenuAction implements Action {

    @Override
    public String getName() {
        return "Magias & Habilidades";
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.SELF; // valor dummy, nunca usado
    }


    @Override
    public void act(Player actor, Player target) {
        // NÃO FAZ NADA
        // Arena vai tratar isso
    }
}
