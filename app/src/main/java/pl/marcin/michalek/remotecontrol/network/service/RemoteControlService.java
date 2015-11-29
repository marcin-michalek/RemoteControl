package pl.marcin.michalek.remotecontrol.network.service;

import pl.marcin.michalek.remotecontrol.network.ServicePaths;
import pl.michalek.marcin.remotecontrol.dto.ResponseDto;

import retrofit.Callback;
import retrofit.http.POST;

/**
 * Responsible for displaying remote control and doing network requests
 * after buttons click.
 */
public interface RemoteControlService {

    @POST(ServicePaths.CONTROL_SPACE)
    void sendSpaceClick(Callback<ResponseDto> response);

    @POST(ServicePaths.CONTROL_PREV)
    void sendRewindClick(Callback<ResponseDto> response);

    @POST(ServicePaths.CONTROL_NEXT)
    void sendForwardClick(Callback<ResponseDto> response);
}
