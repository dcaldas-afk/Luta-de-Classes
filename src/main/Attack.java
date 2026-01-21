public class Attack implements Action {

    @Override
    public String getName() {
        return "Ataque básico";
    }

    @Override
    public void act(Player player, Player target) {
        int damage = 25;

        target.receiveDamage(damage);

        CombatLog.register(player.getName() + " atacou " + target.getName() + ", causando " + damage + " pontos de dano");

    }
}