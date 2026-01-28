package game.jobs;

import game.action.*;
import game.core.*;
import game.resources.Mana;
import game.skill.Angelus;
import game.skill.Cure;
import game.skill.Fireball;
import game.skill.Haste;
import game.skill.Magnificat;

public class Mage extends Player {

    private static Stats defaultStats() {
        return new Stats (
    2,   // strength
     9,   // agility
    4,   // dexterity
10,  // intelligence
   99,   // vitality
        6    // luck 
        );
    }
    
    public Mage(String name, boolean ifHuman) {
        super(name, 100, ifHuman, defaultStats(), Job.MAGE);
        addResource(new Mana(100));

        actions.add(new Attack());
        actions.add(new SkillMenuAction());
        actions.add(new SkipTurn());

        skillList.add(new Fireball());
        skillList.add(new Haste());
        skillList.add(new Magnificat());
        skillList.add(new Angelus());
        skillList.add(new Cure());
    }

}