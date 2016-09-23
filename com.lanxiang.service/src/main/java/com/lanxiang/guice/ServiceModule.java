package com.lanxiang.guice;

import com.google.inject.name.Names;
import com.lanxiang.model.Address;
import com.lanxiang.model.User;
import com.lanxiang.service.UserService;
import com.lanxiang.service.impl.UserServiceImpl;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by lanxiang on 16/9/1.
 */
public class ServiceModule extends MyBatisModule{

    private Properties properties;

    private void initDbProperties(){
        properties = new Properties();
        ResourceBundle rb = ResourceBundle.getBundle("db");
        properties.setProperty("mybatis.environment.id", "dev");
        properties.setProperty("JDBC.driver", rb.getString("db.classname"));
        properties.setProperty("JDBC.url", rb.getString("db.url"));
        properties.setProperty("JDBC.username", rb.getString("db.username"));
        properties.setProperty("JDBC.password", rb.getString("db.password"));
        properties.setProperty("JDBC.autoCommit", "false");
    }


    protected void initialize() {
        initDbProperties();
        bindDataSourceProviderType(PooledDataSourceProvider.class);
        bindTransactionFactoryType(JdbcTransactionFactory.class);
        addMapperClasses("com.lanxiang.mapper");
        addSimpleAlias(User.class);
        addSimpleAlias(Address.class);
        bind(UserService.class).to(UserServiceImpl.class);
        Names.bindProperties(this.binder(), properties);
    }
}
