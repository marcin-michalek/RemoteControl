package pl.marcin.michalek.remotecontrol.network.service;

import pl.marcin.michalek.remotecontrol.network.ServicePaths;
import pl.michalek.marcin.remotecontrol.dto.MouseMoveParamsDto;
import pl.michalek.marcin.remotecontrol.dto.ResponseDto;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Responsible for doing network requests after buttons click.
 */
public interface RemoteControlService {

    @POST(ServicePaths.KEYBOARD_SPACE)
    Call<ResponseDto> sendSpaceClick();

    @POST(ServicePaths.KEYBOARD_PREV)
    Call<ResponseDto> sendRewindClick();

    @POST(ServicePaths.KEYBOARD_NEXT)
    Call<ResponseDto> sendForwardClick();

    @POST(ServicePaths.MOUSE_MOVE)
    Call<ResponseDto> sendMouseMoveParams(@Body MouseMoveParamsDto mouseMoveParamsDto);

    @POST(ServicePaths.MOUSE_LMB)
    Call<ResponseDto> sendLmbClick();

    @POST(ServicePaths.MOUSE_LMB2x)
    Call<ResponseDto> sendLmb2xClick();

    @POST(ServicePaths.MOUSE_RMB)
    Call<ResponseDto> sendRmbClick();
}
