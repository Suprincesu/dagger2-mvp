package rts.com.np.daggerexample;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import rts.com.np.daggerexample.login.LoginActivityMVP;
import rts.com.np.daggerexample.login.LoginActivityPresenter;
import rts.com.np.daggerexample.login.User;

public class PresenterTests {

    LoginActivityMVP.Model mockLoginModel;
    LoginActivityMVP.View mockView;
    LoginActivityPresenter presenter;
    User user;

    @Before
    public void setup(){

        mockLoginModel = Mockito.mock(LoginActivityMVP.Model.class);

        user=new User("Fox","Mulder");

        //Mockito.when(mockLoginModel.getUser()).thenReturn(user);

        mockView=Mockito.mock(LoginActivityMVP.View.class);

        presenter=new LoginActivityPresenter(mockLoginModel);

        presenter.setView(mockView);
    }

//    @Test
//    public void noInteractionWithView(){
//        presenter.getCurrentUser();
//
//        Mockito.verifyZeroInteractions(mockView);
//    }

    @Test
    public void loadTheUserFromTheRepositoryWhenValidUserIsPresent(){
        Mockito.when(mockLoginModel.getUser()).thenReturn(user);

        presenter.getCurrentUser();

        //verify model interactions
        Mockito.verify(mockLoginModel,Mockito.times(1)).getUser();

        //verify view interactions
        Mockito.verify(mockView,Mockito.times(1)).setFirstName("Fox");
        Mockito.verify(mockView,Mockito.times(1)).setLastName("Mulder");
        Mockito.verify(mockView,Mockito.never()).showUserNotAvailable();
    }

    @Test
    public void shouldShowErrorMessageWhenUserIsNull(){
        Mockito.when(mockLoginModel.getUser()).thenReturn(null);

        presenter.getCurrentUser();

        //verify model interactions
        Mockito.verify(mockLoginModel,Mockito.times(1)).getUser();

        //verify view interactions
        Mockito.verify(mockView,Mockito.never()).setFirstName("Fox");
        Mockito.verify(mockView,Mockito.never()).setLastName("Mulder");
        Mockito.verify(mockView,Mockito.times(1)).showUserNotAvailable();
    }

    @Test
    public void shouldCreateErrorMessageIfFieldAreEmpty(){
        Mockito.when(mockView.getFirstName()).thenReturn("");

        presenter.saveUser();

        Mockito.verify(mockView,Mockito.times(1)).getFirstName();
        Mockito.verify(mockView,Mockito.times(1)).getLastName();
        Mockito.verify(mockView,Mockito.times(1)).showInputError();

        //Now tell mockView to return a value for first name and an empty last name
        Mockito.when(mockView.getFirstName()).thenReturn("Dana");
        Mockito.when(mockView.getLastName()).thenReturn("");

        presenter.saveUser();

        Mockito.verify(mockView,Mockito.times(2)).getFirstName();
        Mockito.verify(mockView,Mockito.times(1)).getLastName();
        Mockito.verify(mockView,Mockito.times(2)).showInputError();
    }
}
