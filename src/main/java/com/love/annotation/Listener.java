package com.love.annotation;

import com.love.event.Event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: mi
 * @CreateTime: 2023/10/26 14:36
 * @Description:
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Listener {
    String value() default "";

    Class<?> eventType() default Event.class;
}
