package game.generator;

import java.util.*;

import game.core.Job;

public class NamePool {

    private static final Map<Job, List<String>> NAMES = Map.of(
        Job.MAGE, new ArrayList<>(List.of("Merlin", "Gandalf", "Vivi", "Rincewind")),
        Job.WARRIOR, new ArrayList<>(List.of("Oak", "Conan", "Leonidas", "Guts")),
        Job.ARCHER, new ArrayList<>(List.of("Legolas", "Robin", "Artemis", "Sylvanas")),
        Job.PRIEST, new ArrayList<>(List.of("Sophia", "Basara", "Angela", "Uriel")),
        Job.ASSASSIN, new ArrayList<>(List.of("Zalera", "Alanis", "Getou", "Sasaki", "Paine"))
    );

    public static String drawName(Job job) {
        List<String> pool = NAMES.get(job);
        if (pool.isEmpty())
            throw new RuntimeException("Sem nomes disponíveis para " + job);

        return pool.remove(new Random().nextInt(pool.size()));
    }
}