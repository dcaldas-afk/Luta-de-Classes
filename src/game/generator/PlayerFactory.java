package game.generator;

import java.util.*;

import game.core.*;
import game.jobs.*;

public class PlayerFactory {

    private static final Random r = new Random();

    public static Player createRandom(
            boolean isHuman,
            int minLevel,
            int maxLevel,
            Job job
    ) {
        int level = r.nextInt(maxLevel - minLevel + 1) + minLevel;
        String name = NamePool.drawName(job);

        return switch (job) {
            case MAGE -> new Mage(name, isHuman, level);
            case WARRIOR -> new Warrior(name, isHuman, level);
            case ARCHER -> new Archer(name, isHuman, level);
            case PRIEST -> new Priest(name, isHuman, level);
            case ASSASSIN -> new Assassin(name, isHuman, level);
        };
    }
}