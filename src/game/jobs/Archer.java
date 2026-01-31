package game.jobs;

import game.action.*;
import game.core.*;
import game.resources.Mana;
import game.skill.*;

public class Archer extends Player {

    private static Stats defaultStats() {
        return new Stats (
    16,   // strength
     12,   // agility
    4,   // dexterity
10,  // intelligence
   20,   // vitality
        60    // luck 
        );
    }
    
    public Archer(String name, boolean ifHuman) {
        super(name, 100, ifHuman, defaultStats(), Job.ARCHER);
        addResource(new Mana(100));

        actions.add(new Attack());
        actions.add(new SkillMenuAction());
        actions.add(new SkipTurn());

        skillList.add(new DoubleStrafe());
        skillList.add(new SonicBlow());
        skillList.add(new MeteorAssault());
    }
}