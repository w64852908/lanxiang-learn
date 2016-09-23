package fieldjnject;

import module.PrivateMyBatisModule;

/**
 * Created by lanxiang on 16/9/5.
 */
public class SayHelloModule extends PrivateMyBatisModule {
    protected Class[] mapperClasses() {
        return new Class[0];
    }

    @Override
    protected void configure() {
        super.configure();
        register(SayHello.class, SayHelloImpl.class);
    }
}
