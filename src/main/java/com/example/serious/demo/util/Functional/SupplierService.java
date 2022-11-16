package com.example.serious.demo.util.Functional;

import java.util.function.Supplier;

@FunctionalInterface
public interface SupplierService {

    void noParamReturnHandle(Supplier<String> supplier);
}
