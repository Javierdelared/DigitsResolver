package com.digits.resolver.service;

import com.digits.resolver.model.Combination;
import com.digits.resolver.model.CombinatorRecord;
import com.digits.resolver.model.Solution;
import com.digits.resolver.utils.CombinatorUtils;
import com.digits.resolver.utils.ValidatorUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DigitResolverService {

    private final ValidatorUtils validatorUtils;
    private final CombinatorUtils combinatorUtils;

    public DigitResolverService(ValidatorUtils validatorUtils, CombinatorUtils combinatorUtils) {
        this.validatorUtils = validatorUtils;
        this.combinatorUtils = combinatorUtils;
    }

    public Combination getBestSolution(List<Integer> numbers, Integer target) {
        validatorUtils.validateNumbersAndTarget(numbers, target);
        CombinatorRecord combinatorRecord = new CombinatorRecord.Builder(numbers, target).build();
        Solution solution = combinatorUtils.calculateResults(combinatorRecord, false);
        combinatorUtils.logSolution(solution);
        return solution.getBestCombinationSolution();
    }
}
