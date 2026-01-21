import java.util.*;

public class Main {

    public static void main(String[] args) {

        // ===== Criando combatentes =====
        Player merlin = new Mage("Merlin", true);
        Player gandalf = new Mage("Gandalf", false);
        Player vivi = new Mage("Vivi", true);

        // ===== Criando as parties =====
        Party partyA = new Party(List.of(merlin, vivi));
        Party partyB = new Party(List.of(gandalf));

        // ===== Criando a arena =====
        Arena arena = new Arena(partyA, partyB);

        // ===== Iniciando o combate =====
        arena.start();
    }
}
