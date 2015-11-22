package pl.marcin.michalek.remotecontrol.network;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Class responsible for providing network services.
 */
public class ServiceProvider {

        private static RestAdapter.Builder builder = new RestAdapter.Builder()
                    .setEndpoint(ServicePaths.ROOT_REST_URL)
                    .setClient(new OkClient(new OkHttpClient()));

        public static <S> S provideService(Class<S> serviceClass) {
            RestAdapter adapter = builder.build();
            return adapter.create(serviceClass);
        }
}
