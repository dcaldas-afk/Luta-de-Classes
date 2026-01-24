package game.action;

import game.core.Player;

public interface Action {
    String getName();
    void act(Player player, Player target);
}