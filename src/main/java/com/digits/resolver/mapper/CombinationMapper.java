package com.digits.resolver.mapper;

import com.digits.resolver.dto.CombinationDto;
import com.digits.resolver.model.Combination;
import com.digits.resolver.operations.Operation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CombinationMapper {

    public CombinationDto toDto(Combination combination) {
        if (combination == null) {
            return null;
        }
        CombinationDto combinationDto = new CombinationDto();
        List<String> mappedNecessaryOperations = combination.getNecessaryOperations().stream().map(Operation::toString)
                .collect(Collectors.toList());
        combinationDto.setNecessaryOperations(mappedNecessaryOperations);
        combinationDto.setNecessaryNumbers(combination.getNecessaryNumbers().toString());
        return combinationDto;
    }
}
