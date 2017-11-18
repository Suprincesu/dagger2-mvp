package rts.com.np.daggerexample.http;

import retrofit2.Call;
import retrofit2.http.GET;
import rts.com.np.daggerexample.http.apimodel.Twitch;

public interface TwitchAPI {

    @GET("games/top")
    Call<Twitch> getTopGames();
}
