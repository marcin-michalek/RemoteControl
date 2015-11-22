package pl.marcin.michalek.remotecontrol.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Class responsible for managing SharedPreferences.
 */
public class Prefs {

    private static final String KEY_LAST_USED_IP = "keyLastUsedIp";

    private static SharedPreferences getSharePreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return getSharePreferences(context).edit();
    }

    public static void putLastUsedIp(Context context, String ip) {
        getEditor(context)
            .putString(KEY_LAST_USED_IP, ip)
            .apply();
    }

    public static boolean hasLastUsedIp(Context context) {
        return getSharePreferences(context).contains(KEY_LAST_USED_IP);
    }

    public static String getLastUsedIp(Context context) {
        return getSharePreferences(context)
            .getString(KEY_LAST_USED_IP, "");
    }
}
