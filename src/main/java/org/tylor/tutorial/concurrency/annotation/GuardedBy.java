package org.tylor.tutorial.concurrency.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 标名锁的对象
 */
@Documented
@Target({ElementType.METHOD,ElementType.FIELD})
public @interface GuardedBy {
    String value();
}
