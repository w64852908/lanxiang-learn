package fieldjnject;

import com.google.inject.Guice;
import com.google.inject.Inject;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by lanxiang on 16/9/5.
 */
public class TestInject {

    @Inject
    SayHello sayHello;

    @Before
    public void initModule(){
        sayHello = Guice.createInjector(new SayHelloModule()).getInstance(SayHello.class);
    }

    @Test
    public void injectTest() {
        sayHello.hello();
    }
}
