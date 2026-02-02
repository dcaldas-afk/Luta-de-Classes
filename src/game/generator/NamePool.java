package game.generator;

import game.core.Job;
import java.util.*;

public class NamePool {

    private static final Map<Job, List<String>> NAMES = Map.of(
        Job.MAGE, new ArrayList<>(List.of("Merlin", "Gandalf", "Vivi", "Rincewind", "Elminster", "Raistlin", "Prospero", "Mordenkainen", "Saruman", "Medivh", "Zatara", "Allanon", "Pug", "Belgarath", "Adeptus", "Thoth", "Circe", "Agrippa", "Paracelsus", "Nostradamus")),
        Job.WARRIOR, new ArrayList<>(List.of("Oak", "Conan", "Leonidas", "Guts", "Siegfried", "Arthas", "Roland", "Lancelot", "Bjorn", "Achilles", "Drake", "Einar", "Aldric", "Thorin", "Cedric", "Valen", "Hector", "Ragnar", "Caelum", "Baldur")),
        Job.ARCHER, new ArrayList<>(List.of("Legolas", "Robin", "Artemis", "Sylvanas", "Irvine", "Aloy", "Hawkeye", "Atalanta", "Nasuada", "Fenn", "Kael", "Nyssa", "Faelan", "Emily", "Elowen", "Tauriel", "Skadi", "Rowan", "Alaric", "Vesper")),
        Job.PRIEST, new ArrayList<>(List.of("Sophia", "Basara", "Angela", "Uriel", "Rasputin", "Sakura", "Athanasius", "Eliora", "Seraphine", "Cassiel", "Malachai", "Anastasia", "Iskandar", "Theodora", "Raphael", "Selene", "Ilyas", "Bethany", "Orion", "Kaleb")),
        Job.ASSASSIN, new ArrayList<>(List.of("Zalera", "Alanis", "Getou", "Sasaki", "Paine", "Killua", "Sasuke", "Naruto", "Altair", "Akira", "Ryu", "Shin", "Shadow", "Noctis", "Hanzo", "Kain", "Nyx", "Raizo", "Izan", "Serena"))
    );

    public static String drawName(Job job) {
        List<String> pool = NAMES.get(job);
        if (pool.isEmpty())
            throw new RuntimeException("Sem nomes disponíveis para " + job);

        return pool.remove(new Random().nextInt(pool.size()));
    }
}