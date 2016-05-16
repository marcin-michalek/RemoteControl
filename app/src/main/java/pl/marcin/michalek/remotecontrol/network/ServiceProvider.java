package pl.marcin.michalek.remotecontrol.network;

import com.toddfast.util.preconditions.Preconditions;

import pl.marcin.michalek.remotecontrol.network.service.RemoteControlService;
import pl.marcin.michalek.remotecontrol.network.service.ServerVersionService;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Class responsible for providing network services.
 */
public class ServiceProvider {

    private static Retrofit retrofit;

    public static void buildRetrofit(String baseUrl) {
        retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    public static ServerVersionService provideServerVersionService() {
        Preconditions.isNotNull(retrofit);
        return retrofit.create(ServerVersionService.class);
    }

    public static RemoteControlService provideRemoteControlService() {
        Preconditions.isNotNull(retrofit);
        return retrofit.create(RemoteControlService.class);
    }
}
