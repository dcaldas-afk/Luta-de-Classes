package game.jobs;

import game.action.*;
import game.core.*;
import game.resources.Mana;
import game.skill.*;

public class Mage extends Player {

    private static Stats defaultStats() {
        return new Stats (
    3,   // força
     7,   // agilidade
    4,   // vitalidade
18,  // inteligência
   7,  // destreza
        11    // sorte
        );
    }
    
    public Mage(String name, boolean ifHuman, int level) {
        super(name, ifHuman, defaultStats(), Job.MAGE, level);
        balance(level);
        calcHP();
        
        int maxMP = 15 + stats.getIntelligence()*3;
        addResource(new Mana(maxMP));

        actions.add(new Attack());
        actions.add(new SkillMenuAction());
        actions.add(new SkipTurn());

        skillList.add(new Fireball());
        skillList.add(new Blizzard());
        skillList.add(new MeteorStorm());
        skillList.add(new SightTrasher());
        skillList.add(new JupitelThunder());
        skillList.add(new HeavensDrive());
    }
    
    public void balance(int level) {
        stats.addStrength(Math.floor(level*0.8));
        stats.addAgility(Math.floor(level*1.2));
        stats.addVitality(Math.floor(level*1.1));
        stats.addIntelligence(Math.floor(level*2));
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