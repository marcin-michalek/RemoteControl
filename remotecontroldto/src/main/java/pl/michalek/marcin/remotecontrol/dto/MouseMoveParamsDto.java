package pl.michalek.marcin.remotecontrol.dto;

/**
 * Dto for transferring parameters of mouse event between down and up touch.
 */
public class MouseMoveParamsDto {

    private PointDto down;
    private PointDto up;
    long timestampDown;
    long timestampUp;

    public PointDto getDown() {
        return down;
    }

    public void setDown(PointDto down) {
        this.down = down;
    }

    public PointDto getUp() {
        return up;
    }

    public void setUp(PointDto up) {
        this.up = up;
    }

    public long getTimestampDown() {
        return timestampDown;
    }

    public void setTimestampDown(long timestampDown) {
        this.timestampDown = timestampDown;
    }

    public long getTimestampUp() {
        return timestampUp;
    }

    public void setTimestampUp(long timestampUp) {
        this.timestampUp = timestampUp;
    }
}
