package pl.marcin.michalek.remotecontrol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import pl.marcin.michalek.remotecontrol.R;
import pl.marcin.michalek.remotecontrol.activity.MainActivity;
import pl.marcin.michalek.remotecontrol.config.Constants;
import pl.marcin.michalek.remotecontrol.network.ServiceProvider;
import pl.marcin.michalek.remotecontrol.network.service.RemoteControlService;
import pl.marcin.michalek.remotecontrol.preferences.Prefs;
import pl.michalek.marcin.remotecontrol.dto.ResponseDto;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * MainActivity responsible for displaying RemoteControl and doing network requests
 * after buttons clicks.
 */
public class KeyboardFragment extends Fragment implements Callback<ResponseDto> {

    @Bind(R.id.pbProgress)
    ProgressBar progressBar;

    @Bind(R.id.tvServerAddress)
    TextView serversAddress;

    RemoteControlService remoteControlService = ServiceProvider.provideRemoteControlService();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_controls, container, false);
        ButterKnife.bind(this, view);
        serversAddress.setText(Prefs.getLastUsedIp(getContext()));
        setHasOptionsMenu(true);
        return view;
    }

    @OnClick(R.id.btnRewind)
    void sendRewindToServer() {
        progressBar.setVisibility(View.VISIBLE);
        remoteControlService.sendRewindClick().enqueue(this);
    }

    @OnClick(R.id.btnSpace)
    void sendSpaceToServer() {
        progressBar.setVisibility(View.VISIBLE);
        remoteControlService.sendSpaceClick().enqueue(this);
    }

    @OnClick(R.id.btnForward)
    void sendForwardToServer() {
        progressBar.setVisibility(View.VISIBLE);
        remoteControlService.sendForwardClick().enqueue(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_with_mouse_toggle, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_mouse:
                ((MainActivity) getActivity()).replaceFragment(new MouseFragment());
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
        Log.e(Constants.LOG_TAG, "Error in sending keyboard control data: " + t.getMessage());
        progressBar.setVisibility(View.GONE);
    }
}
