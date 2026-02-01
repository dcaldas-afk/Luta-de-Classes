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
    public void addStrength(double value)     {strength += value;}
    public void addAgility(double value)      {agility += value;}
    public void addVitality(double value)     {vitality += value;}
    public void addIntelligence(double value) {intelligence += value;}
    public void addDexterity(double value)    {dexterity += value;}
    public void addLuck(double value)         {luck += value;}
}