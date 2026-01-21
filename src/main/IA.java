import java.util.*;

public class IA {
    private static Random rand = new Random();

    public static Action act(Player p) {
        List<Action> actionList = p.getActionList();
        return actionList.get(rand.nextInt(actionList.size()));
    }
}