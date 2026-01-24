package game.core;

public class Stats {
    private int strength;
    private int agility;
    private int vitality;
    private int intelligence;
    private int dexterity;
    private int luck;

    public Stats(int strength, int agility, int vitality, int intelligence, int dexterity, int luck) {
        this.strength = strength;
        this.agility =  agility;
        this.vitality = vitality;
        this.intelligence = intelligence;
        this.dexterity = dexterity;
        this.luck = luck;
    }

    // Getters
    public int getStrength() { 
        return strength; 
    }

    public int getAgility() { 
        return agility; 
    }

    public int getDexterity() { 
        return dexterity; 
    }

    public int getIntelligence() { 
        return intelligence; 
    }

    public int getVitality() { 
        return vitality; 
    }
    
    public int getLuck() { 
        return luck; 
    }
}
