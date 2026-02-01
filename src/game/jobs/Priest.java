package game.jobs;

import game.action.*;
import game.core.*;
import game.resources.Mana;
import game.skill.*;

public class Priest extends Player {

    private static Stats defaultStats() {
        return new Stats (
    2,   // strength
     90,   // agility
    4,   // dexterity
10,  // intelligence
   99,  // vitality
        6    // luck 
        );
    }
    
    public Priest(String name, boolean ifHuman) {
        super(name, 100, ifHuman, defaultStats(), Job.PRIEST);
        addResource(new Mana(100));

        actions.add(new Attack());
        actions.add(new SkillMenuAction());
        actions.add(new SkipTurn());

        skillList.add(new LexDivina());
        skillList.add(new Haste());
        skillList.add(new Magnificat());
        skillList.add(new Angelus());
        skillList.add(new Cure());
        skillList.add(new Gloria());
        skillList.add(new ImpositioManus());
    }
}
