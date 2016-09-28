package com.lanxiang.rabbitmq.resource;


/**
 * Created by lanxiang on 2016/9/28.
 */
public class DefaultRabbitmqConnectionProvider extends AbstractRabbitmqConnectionProvider {

    @Override
    protected String getRabbitmqConfig() {
        return "rabbitmq-cfg";
    }
}
