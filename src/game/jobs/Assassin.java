package game.jobs;

import game.action.*;
import game.core.*;
import game.resources.Mana;
import game.skill.*;

public class Assassin extends Player {

    private static Stats defaultStats() {
        return new Stats (
    8,   // força
     9,   // agilidade
    6,   // vitalidade
5,  // inteligência
   12,   // destreza
        10    // sorte
        );
    }
    
    public Assassin(String name, boolean ifHuman, int level) {
        super(name, ifHuman, defaultStats(), Job.ASSASSIN, level);
        balance(level);
        calcHP();
        
        int maxMP = 15 + stats.getIntelligence()*3;
        addResource(new Mana(maxMP));

        actions.add(new Attack());
        actions.add(new SkillMenuAction());
        actions.add(new SkipTurn());

        //skillList.add(new DoubleStrafe());
        skillList.add(new SonicBlow());
        skillList.add(new MeteorAssault());
        skillList.add(new PoisonKnife());
        skillList.add(new VenomSplasher());
    }

    public void balance(int level) {
      stats.addStrength(Math.floor(level*2));
      stats.addAgility(Math.floor(level*2));
      stats.addVitality(Math.floor(level));
      stats.addIntelligence(Math.floor(level));
      stats.addDexterity(Math.floor(level*2));
      stats.addLuck(Math.floor(level*2));  
    }

    public void calcHP() {
        maxHP = 30 + stats.getVitality()*3;
        setCurrentHP(maxHP);

        if (currentHP >= maxHP)
            currentHP = maxHP;
    }
}