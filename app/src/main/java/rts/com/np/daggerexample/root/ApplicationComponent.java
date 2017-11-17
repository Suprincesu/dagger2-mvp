package rts.com.np.daggerexample.root;

import javax.inject.Singleton;

import dagger.Component;
import rts.com.np.daggerexample.login.LoginActivity;
import rts.com.np.daggerexample.login.LoginModule;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

  void inject(LoginActivity target);
}
