package game;

import game.combat.*;
import game.core.*;
import game.jobs.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // ===== Criando combatentes =====
        Player merlin = new Mage("Merlin", true);
        Player gandalf = new Mage("Gandalf", false);
        Player vivi = new Mage("Vivi", false);
        Player oak = new Warrior("Oak", true);

        // ===== Criando as parties =====
        Party partyA = new Party(List.of(merlin, oak));
        Party partyB = new Party(List.of(gandalf, vivi));

        // ===== Criando a arena =====
        Arena arena = new Arena(partyA, partyB);

        // ===== Iniciando o combate =====
        arena.start();
    }
}
