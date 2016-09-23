package providerinject;

import com.google.inject.Guice;
import org.junit.Test;

/**
 * Created by lanxiang on 16/9/8.
 */
public class InjectProviderTest {
    @Test
    public void injectProvider() {
        User user = Guice.createInjector(new ProviderModule()).getProvider(User.class).get();
        System.out.println(user.toString());
    }
}
