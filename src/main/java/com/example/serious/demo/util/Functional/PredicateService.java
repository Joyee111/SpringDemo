package com.example.serious.demo.util.Functional;

import java.util.List;
import java.util.function.Predicate;

@FunctionalInterface
public interface PredicateService {

    List<String> assertString(Predicate<String> predicate);
}
