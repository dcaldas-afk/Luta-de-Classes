package game.combat;

import game.core.Player;

@FunctionalInterface
public interface DamageFormula {
    int calculate(Player attacker, Player target);
}
