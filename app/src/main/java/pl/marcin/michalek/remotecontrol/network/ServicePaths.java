package pl.marcin.michalek.remotecontrol.network;

import pl.marcin.michalek.remotecontrol.BuildConfig;

/**
 * Class holding paths to server-side services.
 */
public final class ServicePaths {

    public static final String ROOT_REST_URL = BuildConfig.ROOT_REST_URL;
    public static final String CONTROL = "/control";
    public static final String CONTROL_SPACE = CONTROL + "/space";
    public static final String CONTROL_PREV = CONTROL + "/prev";
    public static final String CONTROL_NEXT = CONTROL + "/next";
}
