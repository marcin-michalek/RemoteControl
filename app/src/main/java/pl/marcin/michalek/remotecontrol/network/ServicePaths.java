package pl.marcin.michalek.remotecontrol.network;

/**
 * Class holding paths to server-side services.
 */
public final class ServicePaths {

    public static String ROOT_REST_URL = "/** isSetInEnterIpFragment */";
    public static final String CONTROL = "/control";
    public static final String CONTROL_SPACE = CONTROL + "/space";
    public static final String CONTROL_PREV = CONTROL + "/rewind";
    public static final String CONTROL_NEXT = CONTROL + "/forward";
}
