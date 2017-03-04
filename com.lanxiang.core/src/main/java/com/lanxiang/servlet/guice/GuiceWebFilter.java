package com.lanxiang.servlet.guice;

import com.google.inject.servlet.GuiceFilter;

import javax.servlet.annotation.WebFilter;

/**
 * Created by lanxiang on 2016/11/21.
 */

@WebFilter("/*")
public class GuiceWebFilter extends GuiceFilter{
}
