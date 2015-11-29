package pl.marcin.michalek.remotecontrol.util;

import android.graphics.Point;

/**
 * Contains utility methods for Point calculations.
 */
public class PointUtil {

    private PointUtil() {
    }

    public static int distance(Point first, Point second) {
        return (int) Math.sqrt(
            Math.pow(second.x - first.x, 2) +
            Math.pow(second.y - first.y, 2));
    }

    public static int angleInDegrees(Point first, Point second) {
        return (int) Math.toDegrees(Math.atan2(second.x - first.x, second.y - first.y));
    }
}
