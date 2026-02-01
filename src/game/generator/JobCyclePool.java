package game.generator;

import game.core.Job;
import java.util.*;

public class JobCyclePool {

    private final List<Job> available = new ArrayList<>();
    private final Random random = new Random();

    public JobCyclePool() {
        refill();
    }

    private void refill() {
        available.clear();
        available.addAll(Arrays.asList(Job.values()));
        Collections.shuffle(available);
    }

    public Job nextJob() {
        if (available.isEmpty()) {
            refill();
        }
        return available.remove(0);
    }
}