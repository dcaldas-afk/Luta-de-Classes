import java.util.*;

public class Menu {
    private static Scanner sc = new Scanner(System.in);
    
    public static Action select(Player p) {
        List<Action> actionList = p.getActionList();

        for (int i = 0; i < actionList.size(); i++) {
            System.out.println((i+1) + " - " + actionList.get(i).getName());
        }
        System.out.print("> ");
        int selection = sc.nextInt();
        return actionList.get(selection - 1);
    }
}