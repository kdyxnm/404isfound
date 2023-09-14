package com.unimelb.swen90007.group404notfound.api.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NormalToken {
    boolean required() default true;
}
