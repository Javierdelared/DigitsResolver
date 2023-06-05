package com.digits.resolver.controller;

import com.digits.resolver.dto.CombinationDto;
import com.digits.resolver.mapper.CombinationMapper;
import com.digits.resolver.service.DigitResolverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/digits-resolver")
public class DigitsResolverController {

    private static final Logger LOG = LoggerFactory.getLogger(DigitsResolverController.class);

    private final DigitResolverService digitResolverService;
    private final CombinationMapper combinationMapper;

    public DigitsResolverController(DigitResolverService digitResolverService, CombinationMapper combinationMapper) {
        this.digitResolverService = digitResolverService;
        this.combinationMapper = combinationMapper;
    }

    @GetMapping(value = "/best")
    public CombinationDto getBestSolution(@RequestParam List<Integer> numbers, @RequestParam Integer target) {
        LOG.info("Request received: numbers={}, target={}", numbers, target);
        CombinationDto combination = combinationMapper.toDto(digitResolverService.getBestSolution(numbers, target));
        LOG.info("Response: {}", combination);
        return combination;
    }

}
