package game.jobs;

import game.action.*;
import game.core.*;
import game.resources.Mana;

public class Warrior extends Player {
        private static Stats defaultStats() {
        return new Stats (
    100, // strength
     10,   // agility
    4,   // vitality
10,  // intelligence
   99,   // dexterity
        6    // luck 
        );
    }
    
    public Warrior(String name, boolean ifHuman) {
        super(name, 100, ifHuman, defaultStats(), Job.WARRIOR);
        addResource(new Mana(50));
        actions.add(new Attack());
        actions.add(new SkillMenuAction());
        actions.add(new SkipTurn());
    }
}