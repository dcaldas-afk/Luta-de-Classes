package game.action;

import game.combat.TargetType;
import game.core.Player;

public interface Action {
    String getName();
    TargetType getTargetType();
    void act(Player player, Player target);
}