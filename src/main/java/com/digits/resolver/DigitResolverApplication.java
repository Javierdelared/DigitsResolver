package com.digits.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
import java.util.List;

@SpringBootApplication
public class DigitResolverApplication {

	private static final Logger LOG = LoggerFactory.getLogger(DigitResolverApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DigitResolverApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

			final List<Integer> numbers = List.of(3,9,13,19,20,23);
			final Integer target = 445;
			CombinatorRecord combinatorRecord = new CombinatorRecord.Builder(numbers, target).build();

			Instant start = Instant.now();
			Solution solution = CombinatorUtils.calculateResults(combinatorRecord, false);
			CombinatorUtils.logSolution(solution);
			LOG.info("Time elapsed: {} ms", Instant.now().toEpochMilli() - start.toEpochMilli());

			start = Instant.now();
			solution = CombinatorUtils.calculateResults(combinatorRecord, true);
			CombinatorUtils.logSolution(solution);
			LOG.info("Time elapsed: {} ms", Instant.now().toEpochMilli() - start.toEpochMilli());

		};
	}

}
