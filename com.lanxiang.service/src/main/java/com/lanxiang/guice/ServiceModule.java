package com.lanxiang.guice;

import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import com.lanxiang.mapper.AddressMapper;
import com.lanxiang.mapper.UserMapper;
import com.lanxiang.model.Address;
import com.lanxiang.model.User;
import com.lanxiang.service.MessageService;
import com.lanxiang.service.UserService;
import com.lanxiang.service.impl.MessageServiceImpl;
import com.lanxiang.service.impl.UserServiceImpl;
import com.mongodb.MongoClient;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.builtin.PooledDataSourceProvider;

import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by lanxiang on 16/9/1.
 */
public class ServiceModule extends PrivateMyBatisModule {

    private Properties properties;

    @Override
    protected void configure() {
        super.configure();
        register(UserService.class, UserServiceImpl.class);
        register(MessageService.class, MessageServiceImpl.class);
        initDbProperties();
        Names.bindProperties(this.binder(), properties);
        expose(Datastore.class);
    }

    private void initDbProperties() {
        properties = new Properties();
        ResourceBundle rb = ResourceBundle.getBundle("db");
        properties.setProperty("mybatis.environment.id", "dev");
        properties.setProperty("JDBC.driver", rb.getString("db.classname"));
        properties.setProperty("JDBC.url", rb.getString("db.url"));
        properties.setProperty("JDBC.username", rb.getString("db.username"));
        properties.setProperty("JDBC.password", rb.getString("db.password"));
        properties.setProperty("JDBC.autoCommit", "false");
    }

    protected Class[] mapperClasses() {
        return new Class[]{
                AddressMapper.class,
                UserMapper.class
        };
    }

    @Provides
    @Singleton
    Datastore provideDataStore() {
        Morphia morphia = new Morphia();
        morphia.mapPackage("com.lanxiang.model.mongo");
        return morphia.createDatastore(new MongoClient(), "learn_wbg_demo");
    }
}
