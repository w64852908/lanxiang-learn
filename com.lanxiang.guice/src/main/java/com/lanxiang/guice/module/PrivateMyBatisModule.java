package com.lanxiang.guice.module;

import com.google.inject.PrivateModule;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by lanxiang on 16/9/5.
 */
public abstract class PrivateMyBatisModule extends PrivateModule {

    protected <T> void register(Class<T> serviceInterface, Class<? extends T> serviceImpl) {
        bind(serviceInterface).to(serviceImpl).asEagerSingleton();
        expose(serviceInterface);
        bind(serviceImpl);
        expose(serviceImpl);
    }

    @Override
    protected void configure() {
        install(new MyBatisModule() {
            @Override
            protected void initialize() {
                bindDataSourceProviderType(PooledDataSourceProvider.class);
                bindTransactionFactoryType(JdbcTransactionFactory.class);
                Class<?>[] classes = mapperClasses();
                for (Class<?> cls : classes) {
                    addMapperClass(cls);
                }

                for (Class<?> cls : classes) {
                    expose(cls);
                }
                initDBProperties();
            }
        });
    }

    protected abstract Class[] mapperClasses();

    private void initDBProperties() {
        Properties properties = new Properties();
        ResourceBundle rb = ResourceBundle.getBundle("db");
        properties.setProperty("mybatis.environment.id", "dev");
        properties.setProperty("JDBC.driver", rb.getString("db.classname"));
        properties.setProperty("JDBC.url", rb.getString("db.url"));
        properties.setProperty("JDBC.username", rb.getString("db.username"));
        properties.setProperty("JDBC.password", rb.getString("db.password"));
        properties.setProperty("JDBC.autoCommit", "false");
    }
}
