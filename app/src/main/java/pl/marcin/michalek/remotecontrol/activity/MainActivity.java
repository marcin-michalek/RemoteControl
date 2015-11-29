package pl.marcin.michalek.remotecontrol.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import pl.marcin.michalek.remotecontrol.R;
import pl.marcin.michalek.remotecontrol.fragment.EnterIpFragment;

import butterknife.ButterKnife;

/**
 * MainActivity responsible for displaying RemoteControl and doing network requests
 * after buttons clicks.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        replaceFragment(new EnterIpFragment());
    }

    // TODO search for existing fragments instead of creating new fragments during replace
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.flFragmentContainer, fragment)
            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                 android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            .commit();

    }
}
