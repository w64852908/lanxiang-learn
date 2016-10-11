package com.lanxiang.guice.bindingannotation.annotation;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by lanxiang on 2016/10/11.
 */

@Retention(RUNTIME)
@Target({METHOD,FIELD,PARAMETER})
@BindingAnnotation
public @interface Third {
}