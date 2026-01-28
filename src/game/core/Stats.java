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
    public int getStrength()               {return strength;}
    public int getAgility()                {return agility;}
    public int getDexterity()              {return dexterity;}
    public int getIntelligence()           {return intelligence;}
    public int getVitality()               {return vitality;}
    public int getLuck()                   {return luck;}

    // Adders
    public void addStrength(int value)     {strength += value;}
    public void addAgility(int value)      {agility += value;}
    public void addDexterity(int value)    {dexterity += value;}
    public void addIntelligence(int value) {intelligence += value;}
    public void addVitality(int value)     {vitality += value;}
    public void addLuck(int value)         {luck += value;}
}