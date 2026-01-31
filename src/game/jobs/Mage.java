package game.jobs;

import game.action.*;
import game.core.*;
import game.resources.Mana;
import game.skill.*;

public class Mage extends Player {

    private static Stats defaultStats() {
        return new Stats (
    2,   // força
     9,   // agilidade
    4,   // vitalidade
10,  // inteligência
   99,  // destreza
        6    // sorte
        );
    }
    
    public Mage(String name, boolean ifHuman) {
        super(name, 100, ifHuman, defaultStats(), Job.MAGE);
        addResource(new Mana(100));

        actions.add(new Attack());
        actions.add(new SkillMenuAction());
        actions.add(new SkipTurn());

        skillList.add(new Fireball());
        skillList.add(new Blizzard());
        //skillList.add(new Angelus());
    }

}