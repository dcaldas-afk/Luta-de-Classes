package game.menu;

import game.action.Action;
import game.core.Player;
import game.skill.Skill;
import java.util.*;

public class Menu {

    private static Scanner sc = new Scanner(System.in);

    /* ==== MENU PRINCIPAL ==== */
    public static Action select(Player p) {

        List<Action> actions = p.getActionList();

        while (true) {
            for (int i = 0; i < actions.size(); i++) {
                System.out.println((i + 1) + " - " + actions.get(i).getName());
            }
            System.out.print("> ");

            int choice = sc.nextInt();

            if (choice >= 1 && choice <= actions.size()) {
                return actions.get(choice - 1);
            }

            System.out.println("Opção inválida.");
        }
    }

    /* ==== SUBMENU DE SKILLS ==== */
    public static Skill selectSkill(Player p) {

        List<Skill> skills = p.getSkillList();

        while (true) {
            for (int i = 0; i < skills.size(); i++) {
                Skill s = skills.get(i);
                System.out.println(
                    (i + 1) + " - " + s.getName() +
                    " (Custo: " + s.getCost() + " MP)"
                );
            }
            System.out.println("0 - Voltar");
            System.out.print("> ");

            int choice = sc.nextInt();

            if (choice == 0)
                return null;

            if (choice >= 1 && choice <= skills.size()) {
                Skill s = skills.get(choice - 1);
                if (!s.canUse(p)) {
                    System.out.println("Mana insuficiente.");
                    continue;
                }
                return s;
            }

            System.out.println("Opção inválida.");
        }
    }

    /* ==== SELEÇÃO DE ALVO ==== */
    public static Player selectTarget(List<Player> targets) {

        while (true) {
            for (int i = 0; i < targets.size(); i++) {
                Player p = targets.get(i);
                System.out.println( (i + 1) + " - " + p.getName() + " [" + p.getJob() + "] " + "" + p.getCurrentHP() + "/" + p.getMaxHP() + " HP");
            }
            System.out.println("0 - Voltar");
            System.out.print("> ");

            int choice = sc.nextInt();

            if (choice == 0)
                return null;

            if (choice >= 1 && choice <= targets.size())
                return targets.get(choice - 1);

            System.out.println("Opção inválida.");
        }
    }
}
