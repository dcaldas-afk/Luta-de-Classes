package game.jobs;

import game.action.*;
import game.core.*;
import game.resources.Mana;

public class Archer extends Player {

    private static Stats defaultStats() {
        return new Stats (
    10,   // strength
     12,   // agility
    4,   // dexterity
10,  // intelligence
   99,   // vitality
        6    // luck 
        );
    }
    
    public Archer(String name, boolean ifHuman) {
        super(name, 100, ifHuman, defaultStats(), Job.ARCHER);
        addResource(new Mana(100));

        actions.add(new Attack());
        actions.add(new SkillMenuAction());
        actions.add(new SkipTurn());
    }

}