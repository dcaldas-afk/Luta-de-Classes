package game;

import game.combat.*;
import game.core.*;
import game.generator.*;
import game.ui.CombatLogWindow;

public class Main {

    public static void main(String[] args) {

        // ===== Inicializa a janela de log =====
        CombatLogWindow.init();

        // ===== Criando combatentes =====
        /*Player merlin = new Mage("Merlin", true, 15);
        Player gandalf = new Mage("Gandalf", false, 15);
        Player vivi = new Mage("Vivi", false, 15);
        Player oak = new Warrior("Oak", true, 15);
        Player legolas = new Archer("Legolas", true, 15);
        Player sophia = new Priest("Sophia", true, 15);
        Player basara = new Priest("Basara", false, 15);
        Player zalera = new Assassin("Zalera", true, 15);

        // ===== Criando as parties =====
        Party partyA = new Party(List.of(merlin, sophia, zalera, legolas));
        Party partyB = new Party(List.of(vivi, gandalf, basara, oak));*/
        
        System.out.println("=== Criação da Party A ===");
        Party partyA = PartyFactory.createParty(true);

        System.out.println("\n=== Criação da Party B ===");
        Party partyB = PartyFactory.createParty(false);

        System.out.println("\nVocê jogará com:");
        partyA.printNames();
        // ===== Criando a arena =====
        Arena arena = new Arena(partyA, partyB);

        // ===== Iniciando o combate =====
        arena.start();
    }
}
