package game.jobs;

import game.action.*;
import game.core.*;
import game.skill.Fireball;

public class Mage extends Player {

    private static Stats defaultStats() {
        return new Stats (
    2,   // strength
     10,   // agility
    4,   // dexterity
10,  // intelligence
   99,   // vitality
        6    // luck 
        );
    }
    
    public Mage(String name, boolean ifHuman) {
        super(name, 100, ifHuman, defaultStats());
        actions.add(new Attack());
        actions.add(new SkillMenuAction());
        actions.add(new SkipTurn());

        skillList.add(new Fireball());
    }
}