package pl.marcin.michalek.remotecontrol.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pl.marcin.michalek.remotecontrol.R;

/**
 * MainActivity responsible for displaying RemoteControl and doing network requests
 * after buttons clicks.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
