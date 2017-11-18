package rts.com.np.daggerexample.root;

import javax.inject.Singleton;

import dagger.Component;
import rts.com.np.daggerexample.MainActivity;
import rts.com.np.daggerexample.http.ApiModule;
import rts.com.np.daggerexample.login.LoginModule;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class, ApiModule.class})
public interface ApplicationComponent {

  void inject(MainActivity target);
}
