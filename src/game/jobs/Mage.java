package game.jobs;

import game.action.*;
import game.core.*;

public class Mage extends Player {

    private static Stats defaultStats() {
        return new Stats (
    2,   // strength
     3,   // agility
    4,   // dexterity
10,  // intelligence
   5,   // vitality
        6    // luck 
        );
    }
    
    public Mage(String name, boolean ifHuman) {
        super(name, 100, ifHuman, defaultStats());
        actions.add(new Attack());
        actions.add(new SkipTurn());
    }
}