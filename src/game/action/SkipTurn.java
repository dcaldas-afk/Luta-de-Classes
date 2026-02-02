package game.action;

import game.combat.CombatLog;
import game.combat.TargetType;
import game.core.Player;

public class SkipTurn implements Action {

    @Override
    public String getName() {
        return "Pular turno";
    }

    @Override
    public TargetType getTargetType() {
        return TargetType.SELF;
    }
    
    @Override
    public void act(Player player, Player target) {
        if (player.hasEffect("STUN")) {
            CombatLog.register(player.getName() + " está atordoado e não pode realizar nenhuma ação neste turno");
            return;
        }
        CombatLog.register(player.getName() + " pulou o turno");
    }
}