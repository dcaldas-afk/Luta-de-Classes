public interface Action {
    String getName();
    void act(Player player, Player target);
}