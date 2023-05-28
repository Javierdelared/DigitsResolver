import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.List;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        final List<Integer> numbers = List.of(5,7,8,9,15,20);
        final Integer target = 341;
        CombinatorRecord combinatorRecord = new CombinatorRecord.Builder(numbers, target).build();

        Instant start = Instant.now();
        Solution solution = CombinatorUtils.calculateResults(combinatorRecord, false);
        CombinatorUtils.logSolution(solution);
        LOG.info("Time elapsed: {} ms", Instant.now().toEpochMilli() - start.toEpochMilli());

        start = Instant.now();
        solution = CombinatorUtils.calculateResults(combinatorRecord, true);
        CombinatorUtils.logSolution(solution);
        LOG.info("Time elapsed: {} ms", Instant.now().toEpochMilli() - start.toEpochMilli());
    }
}