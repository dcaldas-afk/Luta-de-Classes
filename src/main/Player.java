import java.util.*;

public abstract class Player {

    protected final String name;
    protected final int maxHP;
    protected int currentHP;

    protected boolean ifHuman;

    protected List<Action> actions = new ArrayList<>();

    private Map<Class<? extends Resource>, Resource> resources = new HashMap<>();

    public Player(String name, int maxHP, boolean ifHuman) {
        if (maxHP <= 0)
            throw new IllegalArgumentException("Os PV precisam ser positivos!");

        this.name = name;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.ifHuman = ifHuman;
    }

    /* ================= VIDA ================= */

    public int receiveDamage(int damage) {
        if (damage <= 0)
            return 0;

        currentHP -= damage;

        if (currentHP < 0) {
            currentHP = 0;
            death();
        }

        return damage;
    }

    public boolean isAlive() {
        return currentHP > 0;
    }

    public void death() {
        CombatLog.register(name + "morreu");
    }

    /* ================= AÇÕES ================= */

    public List<Action> getActionList() {
        return actions;
    }

    public boolean isHuman() {
        return ifHuman;
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

    public String getName() {
        return name;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public int getMaxHP() {
        return maxHP;
    }
}
