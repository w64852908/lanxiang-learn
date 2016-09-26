package com.lanxiang.rabbitmq.async.annotation;

import com.google.inject.BindingAnnotation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by lanxiang on 2016/9/24.
 */

@BindingAnnotation
@Target({TYPE, FIELD, PARAMETER, METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AsyncMark {

}
