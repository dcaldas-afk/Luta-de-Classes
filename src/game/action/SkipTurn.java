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
        CombatLog.register(player.getName() + " pulou o turno");
    }
}