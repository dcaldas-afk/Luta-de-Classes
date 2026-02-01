package game.generator;

import java.util.*;

import game.core.*;

public class PartyFactory {

    private static final Scanner sc = new Scanner(System.in);

    public static Party createParty(boolean isHuman) {

        System.out.print("Quantidade de personagens: ");
        int size = sc.nextInt();

        System.out.print("Level mínimo: ");
        int minLevel = sc.nextInt();

        System.out.print("Level máximo: ");
        int maxLevel = sc.nextInt();

        List<Player> members = new ArrayList<>();
        JobCyclePool jobPool = new JobCyclePool();

        for (int i = 0; i < size; i++) {
            Job job = jobPool.nextJob();
            Player p = PlayerFactory.createRandom(
                    isHuman,
                    minLevel,
                    maxLevel,
                    job
            );
            members.add(p);
        }

        return new Party(members);
    }
}