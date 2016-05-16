package pl.marcin.michalek.remotecontrol.fragment;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import pl.marcin.michalek.remotecontrol.R;
import pl.marcin.michalek.remotecontrol.activity.MainActivity;
import pl.marcin.michalek.remotecontrol.config.Constants;
import pl.marcin.michalek.remotecontrol.network.ServiceProvider;
import pl.marcin.michalek.remotecontrol.network.service.RemoteControlService;
import pl.michalek.marcin.remotecontrol.dto.MouseMoveParamsDto;
import pl.michalek.marcin.remotecontrol.dto.PointDto;
import pl.michalek.marcin.remotecontrol.dto.ResponseDto;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Fragment responsible for displaying mouse and buttons and for reacting to clicks and movement.
 */
public class MouseFragment extends Fragment implements View.OnTouchListener, Callback<ResponseDto> {

    private RemoteControlService remoteControlService =
        ServiceProvider.provideRemoteControlService();
    private MouseMoveParamsDto mouseMoveParamsDto = new MouseMoveParamsDto();

    @Bind(R.id.prgProgress)
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mouse, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        return view;
    }

    @OnTouch(R.id.vTouchPad)
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getActionMasked()) {
            mouseMoveParamsDto.setDown(new PointDto((int) event.getX(), (int) event.getY()));
            mouseMoveParamsDto.setTimestampDown(SystemClock.uptimeMillis());
        } else if (MotionEvent.ACTION_UP == event.getActionMasked()) {
            mouseMoveParamsDto.setUp(new PointDto((int) event.getX(), (int) event.getY()));
            mouseMoveParamsDto.setTimestampUp(SystemClock.uptimeMillis());
            sendMouseMoveToServer();
        }
        return true;
    }

    private void sendMouseMoveToServer() {
        progressBar.setVisibility(View.VISIBLE);
        remoteControlService.sendMouseMoveParams(mouseMoveParamsDto).enqueue(this);
    }

    @OnClick(R.id.btnRight)
    void sendRightClickToServer() {
        progressBar.setVisibility(View.VISIBLE);
        remoteControlService.sendRmbClick().enqueue(this);
    }

    @OnClick(R.id.btnLeft)
    void sendLeftClickToServer() {
        progressBar.setVisibility(View.VISIBLE);
        remoteControlService.sendLmbClick().enqueue(this);
    }

    @OnClick(R.id.btnLeft2x)
    void sendLeftClick2xToServer() {
        progressBar.setVisibility(View.VISIBLE);
        remoteControlService.sendLmb2xClick().enqueue(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_with_keyboard_toggle, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_keyboard:
                ((MainActivity) getActivity()).replaceFragment(new KeyboardFragment());
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResponse(Response<ResponseDto> response, Retrofit retrofit) {
        if (response.isSuccess()) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e(Constants.LOG_TAG, "Error in sending mouse control data: " + t.getMessage());
        progressBar.setVisibility(View.GONE);
    }
}
