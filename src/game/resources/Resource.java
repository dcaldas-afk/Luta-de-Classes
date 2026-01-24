package game.resources;
public interface Resource {
    boolean hasEnough(int cost);
    boolean spend(int cost);
    void regenerate(int value);
    int getCurrent();
    int getMax();
}
