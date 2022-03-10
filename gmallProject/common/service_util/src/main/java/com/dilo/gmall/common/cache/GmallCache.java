package com.dilo.gmall.common.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface GmallCache {

    //是否需要定义属性

    /**
     * 缓存key的前缀
     * @return
     */
    String prefix() default "cache";
}
