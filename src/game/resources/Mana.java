package game.resources;
/* Aqui tem algumas validações pra impedir que o mago recupere mana além do máximo, e para
não aceitar valores negativos no custo ou na própria mana*/
public class Mana implements Resource {

    private final int maxMP;
    private int currentMP;

    public Mana(int maxMP) {
        if (maxMP <= 0) {
            throw new IllegalArgumentException("A Mana precisa ter um valor positivo");
        }
        this.maxMP = maxMP;
        this.currentMP = maxMP;
    }

    @Override
    public boolean hasEnough(int cost) {
        return cost > 0 && currentMP >= cost;
    }

    @Override
    public boolean spend(int cost) {
        if (!hasEnough(cost)) {
            return false;
        }
        currentMP -= cost;
        return true;
    }

    @Override
    public void regenerate(int value) {
        if (value <= 0) return;

        currentMP = Math.min(maxMP, currentMP + value);
    }

    @Override
    public int getCurrent() {
        return currentMP;
    }

    @Override
    public int getMax() {
        return maxMP;
    }

    @Override
    public String toString() {
        return "Mana(" + currentMP + "/" + maxMP + ")";
    }
}
