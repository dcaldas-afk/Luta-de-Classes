package game.core;

import game.action.Action;
import game.buffs.Effect;
import game.combat.CombatLog;
import game.resources.Resource;
import game.skill.*;
import java.util.*;
import game.core.*;

public abstract class Player {

    protected final String name;
    protected int maxHP = 1;
    protected final Job job;
    protected int currentHP;
    protected Stats stats;
    protected int level;

    protected boolean ifHuman;

    protected List<Action> actions = new ArrayList<>();
    protected List<Skill> skillList = new ArrayList<>();
    private List<Effect> effects = new ArrayList<>();
    

    private Map<Class<? extends Resource>, Resource> resources = new HashMap<>();
    

    private int atb = 0;

    public Player(String name, boolean ifHuman, Stats stats, Job job, int level) {
        
        this.name = name;
        this.ifHuman = ifHuman;
        this.stats = stats;
        this.job = job;
        this.level = level;
        this.currentHP = maxHP;
    }

    /* ================= VIDA ================= */

    public int receiveDamage(int damage) {
        int base = damage;
        double buff1 = 0;
        double buff2 = 0;


        if (hasEffect("MANUS")) {
            buff1  = Math.floor(base*0.2);
        }

        damage = (int) (base + buff1);
        if (damage <= 0)
            return 0;
        
        currentHP -= damage;

        return damage;
    }

    public boolean isAlive() {
        return currentHP > 0;
    }

    public void ifDeath() {
        if (currentHP <= 0) {
            currentHP = 0;
            CombatLog.register(name + " morreu");
        }
    }

    public void setCurrentHP(int value) {
        currentHP = Math.min(maxHP, value);
    }

    public void increaseCurrentHP(int value) {
        currentHP += value;
    }

    public void increaseMaxHP(int value) {
        maxHP += value;
    }

    public void decreaseMaxHP(int value) {
        maxHP -= value;
        if (maxHP < 1)
            maxHP = 1;
    }

    /* ================= AÇÕES ================= */

    public List<Action> getActionList() {
        return actions;
    }

    public List<Skill> getSkillList() {
        return skillList;
    }

    public boolean isHuman() {
        return ifHuman;
    }

    /* ================= LÓGICA DE ATB ================= */

    public void gainATB() {
        atb += stats.getAgility();
    }

    public boolean isReady() {
        return atb >= 100;
    }

    public void resetATB() {
        atb = 0;
    }

    public int getATB() {
        return atb;
    }

    /* ================= RECURSOS ================= */

    public void addResource(Resource resource) {
        resources.put(resource.getClass(), resource);
    }

    @SuppressWarnings("unchecked")
    public <T extends Resource> T getResource(Class<T> type) {
        return (T) resources.get(type);
    }

    /* ================= GETTERS ================= */

    public String getName()   {return name;}
    public int getCurrentHP() {return currentHP;}
    public int getMaxHP()     {return maxHP;}
    public Stats getStats()   {return stats;}
    public Job getJob()       {return job;}
    public int getLevel()     {return level;} 

    /* ================= BUFFS/DEBUFFS ================= */

    public boolean hasEffect(String effectID) {
        return effects.stream().anyMatch(e -> e.getId().equals(effectID));
    }

    public boolean addEffect(Effect effect) {
        for (Effect e : effects) {
            if (e.getId().equals(effect.getId())) {
                e.refresh(this, effect);
                return false;
            }
        }
        effects.add(effect);
        effect.apply(this);
        return true;
    }

    public void onRoundEnd() {
        Iterator<Effect> it = effects.iterator();
        while(it.hasNext()) {
            Effect e = it.next();
            e.onTurnEnd(this);
            if (e.isExpired()) {
                it.remove();
            }
        }
    }

    public boolean isSilenced() {
        return effects.stream().anyMatch(e -> e.getId().equals("SILENCE"));
    }
}
