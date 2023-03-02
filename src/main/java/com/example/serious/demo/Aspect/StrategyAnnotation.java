package com.example.serious.demo.Aspect;

import org.springframework.stereotype.Indexed;

import java.lang.annotation.*;

/**
 * @author joyee
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Indexed
public @interface StrategyAnnotation {
    String value() default "";
}

