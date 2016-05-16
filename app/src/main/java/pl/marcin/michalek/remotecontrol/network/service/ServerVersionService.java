package pl.marcin.michalek.remotecontrol.network.service;


import pl.marcin.michalek.remotecontrol.network.ServicePaths;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Responsible for getting server version.
 */
public interface ServerVersionService {

    @GET(ServicePaths.SERVER_VERSION)
    Call<Integer> getServerVersion();
}
