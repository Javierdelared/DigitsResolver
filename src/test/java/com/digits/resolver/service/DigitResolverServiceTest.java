package com.digits.resolver.service;

import com.digits.resolver.model.Combination;
import com.digits.resolver.model.Solution;
import com.digits.resolver.utils.CombinatorUtils;
import com.digits.resolver.utils.ValidatorUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class DigitResolverServiceTest {

    @InjectMocks
    private DigitResolverService digitResolverService;

    @Mock
    private ValidatorUtils validatorUtils;
    @Mock
    private CombinatorUtils combinatorUtils;

    @Test
    void getBestSolution_ok_thenReturnsValidResponse() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3);
        int target = 6;
        Combination expectedCombination = new Combination();
        Solution solution = mock(Solution.class);
        when(solution.getBestCombinationSolution()).thenReturn(expectedCombination);
        when(combinatorUtils.calculateResults(any(), eq(false))).thenReturn(solution);
        // When
        Combination combination = digitResolverService.getBestSolution(numbers, target);
        // Then
        verify(validatorUtils).validateNumbersAndTarget(numbers,target);
        verify(combinatorUtils).calculateResults(any(), eq(false));
        assertEquals(expectedCombination, combination);
    }

}
