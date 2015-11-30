package pl.marcin.michalek.remotecontrol.network.service;

import pl.marcin.michalek.remotecontrol.network.ServicePaths;
import pl.michalek.marcin.remotecontrol.dto.MouseMoveParamsDto;
import pl.michalek.marcin.remotecontrol.dto.ResponseDto;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Responsible for doing network requests after buttons click.
 */
public interface RemoteControlService {

    @POST(ServicePaths.KEYBOARD_SPACE)
    void sendSpaceClick(Callback<ResponseDto> response);

    @POST(ServicePaths.KEYBOARD_PREV)
    void sendRewindClick(Callback<ResponseDto> response);

    @POST(ServicePaths.KEYBOARD_NEXT)
    void sendForwardClick(Callback<ResponseDto> response);

    @POST(ServicePaths.MOUSE_MOVE)
    void sendMouseMoveParams(@Body MouseMoveParamsDto mouseMoveParamsDto,
                             Callback<ResponseDto> response);

    @POST(ServicePaths.MOUSE_LMB)
    void sendLmbClick(Callback<ResponseDto> response);

    @POST(ServicePaths.MOUSE_LMB2x)
    void sendLmb2xClick(Callback<ResponseDto> response);

    @POST(ServicePaths.MOUSE_RMB)
    void sendRmbClick(Callback<ResponseDto> response);
}
