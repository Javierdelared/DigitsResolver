package main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.List;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        Instant start = Instant.now();
        final List<Integer> numbers = List.of(4,5,7,11,15,20);
        final Integer target = 321;
        Combiner combiner = new Combiner.Builder(numbers, target).build();
        combiner.logResult();
        LOG.info("Time elapsed: {} ms", Instant.now().toEpochMilli() - start.toEpochMilli());
    }
}