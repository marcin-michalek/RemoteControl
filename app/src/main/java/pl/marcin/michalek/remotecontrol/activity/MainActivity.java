package pl.marcin.michalek.remotecontrol.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.marcin.michalek.remotecontrol.R;
import pl.marcin.michalek.remotecontrol.network.ServiceProvider;
import pl.marcin.michalek.remotecontrol.network.service.RemoteControlService;

/**
 * MainActivity responsible for displaying RemoteControl and doing network requests
 * after buttons clicks.
 */
public class MainActivity extends AppCompatActivity {

    RemoteControlService remoteControlService =
        ServiceProvider.provideService(RemoteControlService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
