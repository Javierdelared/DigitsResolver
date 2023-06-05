package com.digits.resolver.exception;

public class TrivialSolutionException extends ServiceException {
        public TrivialSolutionException() {
            super("The solution is trivial");
        }
}
