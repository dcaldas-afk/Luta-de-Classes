package game.jobs;

import game.action.*;
import game.core.*;
import game.resources.Mana;
import game.skill.*;

public class Archer extends Player {

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
    
    public Archer(String name, boolean ifHuman, int level) {
        super(name, ifHuman, defaultStats(), Job.ARCHER, level);
        balance(level);
        calcHP();
        
        int maxMP = 15 + stats.getIntelligence()*3;
        addResource(new Mana(maxMP));

        actions.add(new Attack());
        actions.add(new SkillMenuAction());
        actions.add(new SkipTurn());

        skillList.add(new DoubleStrafe());
        skillList.add(new Sharpshooting());
        skillList.add(new ArrowShower());
        skillList.add(new ChargeArrow());
        skillList.add(new Concentration());
        skillList.add(new Windwalk());
    }

    public void balance(int level) {
      stats.addStrength(Math.floor(level*1.2));
      stats.addAgility(Math.floor(level*2));
      stats.addVitality(Math.floor(level*1.4));
      stats.addIntelligence(Math.floor(level*1.3));
      stats.addDexterity(Math.floor(level*2.5));
      stats.addLuck(Math.floor(level*2));  
    }

    public void calcHP() {
        maxHP = 30 + stats.getVitality()*3;
        setCurrentHP(maxHP);

        if (currentHP >= maxHP)
            currentHP = maxHP;
    }
}