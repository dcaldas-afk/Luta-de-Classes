package game.resources;
/*Pontos de skill para habilidades físicas. Não precisa funcionar igual à mana, aqui tem um delay na recuperação,
mas pode só não recuperar mesmo, sei lá */
public class SP implements Resource {

    private final int maxSP;
    private int currentSP;

    public SP(int maxSP) {
        if (maxSP <= 0) {
            throw new IllegalArgumentException("O SP precisa possuir valor positivo");
        }
        this.maxSP = maxSP;
        this.currentSP = maxSP;
    }

    @Override
    public boolean hasEnough(int cost) {
        return cost > 0 && currentSP >= cost;
    }

    @Override
    public boolean spend(int cost) {
        if (!hasEnough(cost)) {
            return false;
        }
        currentSP -= cost;
        return true;
    }

    @Override
    public void regenerate(int value) {
        if (value <= 0) return;

        // SP regenera mais devagar
        int regenValue = value / 2;
        if (regenValue <= 0) return;

        currentSP = Math.min(maxSP, currentSP + regenValue);
    }

    @Override
    public int getCurrent() {
        return currentSP;
    }

    @Override
    public int getMax() {
        return maxSP;
    }

    @Override
    public String toString() {
        return "SP(" + currentSP + "/" + maxSP + ")";
    }
}
