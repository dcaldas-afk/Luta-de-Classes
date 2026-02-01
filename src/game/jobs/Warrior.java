package game.jobs;

import game.action.*;
import game.core.*;
import game.resources.Mana;
import game.skill.*;

public class Warrior extends Player {

    private static Stats defaultStats() {
        return new Stats (
            10,   // força
            7,   // agilidade
           20,   // vitalidade
       2,  // inteligência
          7,  // destreza
               4    // sorte
        );
    }
    
    public Warrior(String name, boolean ifHuman, int level) {
        super(name, ifHuman, defaultStats(), Job.WARRIOR, level);
        balance(level);
        calcHP();
        int maxMP = 15 + stats.getIntelligence()*3;
        addResource(new Mana(maxMP));
        
        actions.add(new Attack());
        actions.add(new SkillMenuAction());
        actions.add(new SkipTurn());
    }

    public void balance(int level) {
        stats.addStrength(Math.floor(level));
        stats.addAgility(Math.floor(level*1.3));
        stats.addVitality(Math.floor(level*2.8));
        stats.addIntelligence(Math.floor(level*1.2));
        stats.addDexterity(Math.floor(level*2.5));
        stats.addLuck(Math.floor(level));  
    }

    public void calcHP() {
        maxHP = 30 + stats.getVitality()*3;
        setCurrentHP(maxHP);

        if (currentHP >= maxHP)
            currentHP = maxHP;
    }
}