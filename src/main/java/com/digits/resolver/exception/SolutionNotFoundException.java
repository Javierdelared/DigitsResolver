package com.digits.resolver.exception;

public class SolutionNotFoundException extends ServiceException {
        public SolutionNotFoundException() {
            super("Solution not found");
        }
}
