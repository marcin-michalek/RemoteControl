package pl.marcin.michalek.remotecontrol.network;

/**
 * Class holding paths to server-side services.
 */
public final class ServicePaths {

    /** isSetInEnterIpFragment */
    public static String ROOT_REST_URL = "";

    // Rest of the service paths relative to ROOT REST URL
    public static final String KEYBOARD = "/keyboard";
    public static final String KEYBOARD_SPACE = KEYBOARD + "/space";
    public static final String KEYBOARD_PREV = KEYBOARD + "/rewind";
    public static final String KEYBOARD_NEXT = KEYBOARD + "/forward";

    public static final String MOUSE = "/mouse";
    public static final String MOUSE_MOVE = MOUSE + "/move";
    public static final String MOUSE_LMB = MOUSE + "/lmb";
    public static final String MOUSE_LMB2x = MOUSE + "/lmb2x";
    public static final String MOUSE_RMB = MOUSE + "/rmb";
}
