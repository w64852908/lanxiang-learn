package com.lanxiang.mybatis.guice;

import com.google.inject.PrivateModule;
import com.google.inject.name.Names;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import java.util.Properties;

/**
 * Created by lanxiang on 2017/1/23.
 */
public abstract class MySqlModule extends PrivateModule {

    @Override
    protected void configure() {
        initMappers();
    }

    private void initMappers() {
        install(new MyBatisModule() {
            @Override
            protected void initialize() {
                bindDataSourceProviderType(PooledDataSourceProvider.class);
                bindTransactionFactoryType(JdbcTransactionFactory.class);
                Class[] classes = mapperClasses();
                for (Class mapper : classes) {
                    addMapperClass(mapper);
                }
                Names.bindProperties(this.binder(), getMyBatisProperties());
                for (Class<?> cls : classes) {
                    expose(cls);
                }
            }
        });
    }

    protected abstract Class[] mapperClasses();

    private Properties getMyBatisProperties() {
        final Properties myBatisProperties = new Properties();
        myBatisProperties.setProperty("mybatis.environment.id", "test");
        myBatisProperties.setProperty("JDBC.driver",
                "com.mysql.jdbc.Driver");
        myBatisProperties.setProperty("JDBC.url",
                "jdbc:mysql://127.0.0.1:3306/lanxiangtest?useUnicode=true&amp;characterEncoding=UTF-8");
        myBatisProperties.setProperty("JDBC.username", "root");
        myBatisProperties.setProperty("JDBC.password", "lanxiang1993");
        myBatisProperties.setProperty("JDBC.autoCommit", "true");
        return myBatisProperties;
    }
}
