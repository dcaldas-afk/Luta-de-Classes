package game.combat;

import game.action.Action;
import game.core.Player;
import game.skill.Skill;

import java.util.*;

public class IA {
    private static Random rand = new Random();

    public static Action act(Player p) {
        List<Action> actionList = p.getActionList();
        return actionList.get(rand.nextInt(actionList.size()));
    }

    public static Skill selectSkill(Player p) {
        for (Skill s : p.getSkillList()) {
            if (s.canUse(p))
                return s;
        }
        return null;
    }
}