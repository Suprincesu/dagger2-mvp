package rts.com.np.daggerexample.http;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    public final String BASE_URL="https://api.twitch.tv/kraken/";
    private final String CLIENT_ID="";

    @Provides
    public OkHttpClient provideClient(){
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Interceptor interceptor2=new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request=chain.request().newBuilder().addHeader("Client-ID",CLIENT_ID).build();
                return chain.proceed(request);
            }
        };

        return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(interceptor2).build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseURL,OkHttpClient client){
        return new Retrofit.Builder()
                           .baseUrl(baseURL)
                           .client(client)
                           .addConverterFactory(GsonConverterFactory.create())
                           .build();
    }

    @Provides
    public TwitchAPI provideApiService(){
        return provideRetrofit(BASE_URL,provideClient()).create(TwitchAPI.class);
    }
}
