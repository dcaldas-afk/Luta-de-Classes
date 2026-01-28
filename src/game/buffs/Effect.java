package game.buffs;

import game.core.Player;

public interface Effect {
    String getId();
    void apply(Player p);     // aplicar efeito
    void onTurnEnd(Player p);  
    void onExpire(Player p);
    boolean isExpired();
    default void refresh(Player p, Effect newEffect) {
        // placeholder
    }
}
