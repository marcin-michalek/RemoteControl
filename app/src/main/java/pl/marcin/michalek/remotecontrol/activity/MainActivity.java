package pl.marcin.michalek.remotecontrol.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import pl.marcin.michalek.remotecontrol.R;
import pl.marcin.michalek.remotecontrol.network.ServiceProvider;
import pl.marcin.michalek.remotecontrol.network.service.RemoteControlService;
import pl.michalek.marcin.remotecontrol.dto.ResponseDto;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * MainActivity responsible for displaying RemoteControl and doing network requests
 * after buttons clicks.
 */
public class MainActivity extends AppCompatActivity implements Callback<ResponseDto> {

    @Bind(R.id.pbProgress)
    ProgressBar progressBar;

    RemoteControlService remoteControlService =
        ServiceProvider.provideService(RemoteControlService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnRewind)
    void sendRewindToServer() {
        progressBar.setVisibility(View.VISIBLE);
        remoteControlService.sendRewindClick(this);
    }

    @OnClick(R.id.btnSpace)
    void sendSpaceToServer() {
        progressBar.setVisibility(View.VISIBLE);
        remoteControlService.sendSpaceClick(this);
    }

    @OnClick(R.id.btnForward)
    void sendForwardToServer() {
        progressBar.setVisibility(View.VISIBLE);
        remoteControlService.sendForwardClick(this);
    }

    @Override
    public void success(ResponseDto responseDto, Response response) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void failure(RetrofitError error) {
        progressBar.setVisibility(View.GONE);
    }
}
