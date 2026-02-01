package game.core;

import java.util.*;

public class Party {

    private final List<Player> members;

    public Party(List<Player> members) {
        this.members = new ArrayList<>(members);
    }

    public boolean hasLivingMembers() {
        return members.stream().anyMatch(Player::isAlive);
    }

    public List<Player> getLivingMembers() {
        return members.stream().filter(Player::isAlive).toList();
    }

    public Player randomLivingMember() {
        List<Player> alive = getLivingMembers();
        if (alive.isEmpty())
            return null;
        return alive.get(new Random().nextInt(alive.size()));
    }

    public List<Player> getAllMembers() {
        return members;
    }

    public boolean contains(Player player) {
        return members.contains(player);
    }

    public void printNames() {
        for (int i = 0; i < members.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, members.get(i).getName() + " [" + members.get(i).getJob() + " Lv." + members.get(i).getLevel() + "]");
        }
    }
}
