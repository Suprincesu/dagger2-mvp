package rts.com.np.daggerexample.login;

public interface LoginRepository {
  User getUser();

  void saveUser(User user);
}
