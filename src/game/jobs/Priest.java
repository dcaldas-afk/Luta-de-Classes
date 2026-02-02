package game.jobs;

import game.action.*;
import game.core.*;
import game.resources.Mana;
import game.skill.*;

public class Priest extends Player {

    private static Stats defaultStats() {
        return new Stats (
            6,   // força
            6,   // agilidade
           11,   // vitalidade
       14,  // inteligência
          6,  // destreza
               7    // sorte
        );
    }
    
    public Priest(String name, boolean ifHuman, int level) {
        super(name, ifHuman, defaultStats(), Job.PRIEST, level);
        balance(level);
        calcHP();
        
        int maxMP = 15 + stats.getIntelligence()*3;
        addResource(new Mana(maxMP));
        
        actions.add(new Attack());
        actions.add(new SkillMenuAction());
        actions.add(new SkipTurn());

        skillList.add(new Cure());
        skillList.add(new Blessing());
        skillList.add(new LexDivina());
        skillList.add(new Pneuma());
        skillList.add(new Haste());
        skillList.add(new Magnificat());
        skillList.add(new Angelus());
        skillList.add(new Suffragium());
        skillList.add(new Gloria());
        skillList.add(new ImpositioManus());
    }

    public void balance(int level) {
        stats.addStrength(Math.floor(level*0.9));
        stats.addAgility(Math.floor(level*3.5));
        stats.addVitality(Math.floor(level*2));
        stats.addIntelligence(Math.floor(level*3.5));
        stats.addDexterity(Math.floor(level*1.5));
        stats.addLuck(Math.floor(level*1.5));  
    }

    public void calcHP() {
        maxHP = 30 + stats.getVitality()*3;
        setCurrentHP(maxHP);

        if (currentHP >= maxHP)
            currentHP = maxHP;
    }
}
