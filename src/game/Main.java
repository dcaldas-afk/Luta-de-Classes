package game;

import game.combat.*;
import game.core.*;
import game.jobs.*;
import game.ui.CombatLogWindow;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // ===== Inicializa a janela de log =====
        CombatLogWindow.init();

        // ===== Criando combatentes =====
        Player merlin = new Mage("Merlin", true);
        Player gandalf = new Mage("Gandalf", false);
        Player vivi = new Mage("Vivi", false);
        Player oak = new Warrior("Oak", true);
        Player legolas = new Archer("Legolas", true);
        Player sophia = new Priest("Sophia", true);
        Player basara = new Priest("Basara", true);

        // ===== Criando as parties =====
        Party partyA = new Party(List.of(merlin, legolas, oak, sophia));
        Party partyB = new Party(List.of(vivi, gandalf, basara));

        // ===== Criando a arena =====
        Arena arena = new Arena(partyA, partyB);

        // ===== Iniciando o combate =====
        arena.start();
    }
}
