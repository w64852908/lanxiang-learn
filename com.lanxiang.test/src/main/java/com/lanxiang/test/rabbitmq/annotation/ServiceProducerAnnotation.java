package com.lanxiang.test.rabbitmq.annotation;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * Created by lanxiang on 2016/9/28.
 */
@BindingAnnotation
@Target({FIELD, METHOD, PARAMETER})
@Retention(RUNTIME)
public @interface ServiceProducerAnnotation {

}
