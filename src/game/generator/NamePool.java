package game.generator;

import game.core.Job;
import java.util.*;

public class NamePool {

    private static final Map<Job, List<String>> NAMES = Map.of(
        Job.MAGE, new ArrayList<>(List.of("Merlin", "Gandalf", "Vivi", "Rincewind", "Elminster", "Raistlin", "Prospero", "Mordenkainen", "Saruman", "Medivh", "Zatara", "Allanon", "Pug", "Belgarath", "Adeptus", "Thoth", "Circe", "Agrippa", "Paracelsus", "Nostradamus", "Aleister", "Hermes", "Faustus", "Aurelian", "Iamblichus", "Plotinus", "JohnDee", "Apollonius", "Zoroaster", "Abraxas","Solomon", "Enoch", "Merlinius", "Arcanus", "Heliogabalus", "Taliesin", "Asclepius", "Orpheus", "Pellinore", "Melchior")),
        Job.WARRIOR, new ArrayList<>(List.of("Oak", "Conan", "Leonidas", "Guts", "Siegfried", "Arthas", "Roland", "Lancelot", "Bjorn", "Achilles", "Drake", "Einar", "Aldric", "Thorin", "Cedric", "Valen", "Hector", "Ragnar", "Caelum", "Baldur","Beowulf", "Aeneas", "Perseus", "Theseus", "Ajax", "Odysseus", "Diomedes", "Spartacus", "Scipio", "Caesar","Attila", "Subutai", "ElCid", "WilliamWallace", "RichardLionheart", "Bayard", "Galahad", "Tristan", "Hadrian", "Marcus")),
        Job.ARCHER, new ArrayList<>(List.of("Legolas", "Robin", "Artemis", "Sylvanas", "Irvine", "Aloy", "Hawkeye", "Atalanta", "Nasuada", "Fenn", "Kael", "Nyssa", "Faelan", "Emily", "Elowen", "Tauriel", "Skadi", "Rowan", "Alaric", "Vesper", "Paris", "Apollo", "Ullr", "Hood", "Ygritte", "Karna", "Abhimanyu", "Arjuna", "Herne", "Finnian","Arawn", "Eldric", "Sion", "Liriel", "Caelin", "Brynn", "Aethon", "Nimue", "Seren", "Talor")),
        Job.PRIEST, new ArrayList<>(List.of("Sophia", "Basara", "Angela", "Uriel", "Rasputin", "Sakura", "Athanasius", "Eliora", "Seraphine", "Cassiel", "Malachai", "Anastasia", "Iskandar", "Theodora", "Raphael", "Selene", "Ilyas", "Bethany", "Orion", "Kaleb", "Augustine", "Jerome", "Ambrose", "Origen", "Aquinas", "Benedict", "Francis", "Dominic", "Ignatius", "Anselm", "Isaac", "Ezekiel", "Jeremiah", "Elijah", "Miriam", "Deborah", "Hildegard", "Brigid", "Catherine", "Erasmus")),
        Job.ASSASSIN, new ArrayList<>(List.of("Zalera", "Alanis", "Getou", "Sasaki", "Paine", "Killua", "Sasuke", "Naruto", "Altair", "Akira", "Ryu", "Shin", "Shadow", "Noctis", "Hanzo", "Kain", "Nyx", "Raizo", "Izan", "Ellen", "Hashshashin", "Ezio", "Cassius", "Brutus", "Judas", "Cleon", "Ardashir", "Kuro", "Talon", "Specter","Scorpion", "Viper", "Shade", "Obsidian", "Raven", "Crow", "Silas", "Mordain", "Ashura", "Fenrir"))
    );

    public static String drawName(Job job) {
        List<String> pool = NAMES.get(job);
        if (pool.isEmpty())
            throw new RuntimeException("Sem nomes disponíveis para " + job);

        return pool.remove(new Random().nextInt(pool.size()));
    }
}
