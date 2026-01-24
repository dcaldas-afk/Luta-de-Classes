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
}
