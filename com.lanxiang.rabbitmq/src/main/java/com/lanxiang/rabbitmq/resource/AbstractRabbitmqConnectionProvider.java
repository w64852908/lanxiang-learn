package com.lanxiang.rabbitmq.resource;

import com.google.inject.Provider;
import com.rabbitmq.client.Address;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

/**
 * Created by lanxiang on 2016/9/26.
 */
@Slf4j
public abstract class AbstractRabbitmqConnectionProvider implements Provider<Connection> {

    private Connection connection;

    private void initConnection() {
        if (connection == null) {
            ResourceBundle rb = ResourceBundle.getBundle(getRabbitmqConfig());
            ConnectionFactory factory = new ConnectionFactory();
            factory.setUsername(rb.getString("username"));
            factory.setPassword(rb.getString("password"));
            factory.setVirtualHost(rb.getString("virtual_host"));
            factory.setAutomaticRecoveryEnabled(Boolean.valueOf(rb.getString("automatic_recovery_enable")));
            factory.setNetworkRecoveryInterval(Long.valueOf(rb.getString("network_recovery_interval")));

            try {
                Address[] addresses = Address.parseAddresses(rb.getString("address"));
                connection = factory.newConnection(addresses);
            } catch (IOException e) {
                log.error("Can not create rabbitmq connection, " + e.getMessage());
            } catch (TimeoutException e) {
                log.error("Can not create rabbitmq connection, " + e.getMessage());
            }
        }
    }

    protected abstract String getRabbitmqConfig();

    @Override
    public Connection get() {
        initConnection();
        return connection;
    }
}
