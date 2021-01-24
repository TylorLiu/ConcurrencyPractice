package org.tylor.tutorial.concurrency.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface NotThreadSafe {
}
