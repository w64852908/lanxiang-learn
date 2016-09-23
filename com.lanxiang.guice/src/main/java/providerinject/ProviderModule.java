package providerinject;

import com.google.inject.PrivateModule;

/**
 * Created by lanxiang on 16/9/8.
 */
public class ProviderModule extends PrivateModule {

    protected void configure() {
        bind(User.class).toProvider(UserProvider.class);
        expose(User.class);
    }
}
