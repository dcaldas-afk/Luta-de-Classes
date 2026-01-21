import java.util.*;

public class CombatLog {
    private static List<String> history = new ArrayList<>();

    public static void register(String message) {
        history.add(message);
        System.out.println(message);
    }
}