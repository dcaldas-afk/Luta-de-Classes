package game.combat;

import game.ui.CombatLogWindow;

public class CombatLog {

    public static void register(String message) {
        // continua aparecendo no terminal
        System.out.println(message);

        // vai para a janela
        CombatLogWindow.log(message);
    }
}
