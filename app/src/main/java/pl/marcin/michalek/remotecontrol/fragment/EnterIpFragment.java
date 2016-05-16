package pl.marcin.michalek.remotecontrol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import pl.marcin.michalek.remotecontrol.BuildConfig;
import pl.marcin.michalek.remotecontrol.R;
import pl.marcin.michalek.remotecontrol.activity.MainActivity;
import pl.marcin.michalek.remotecontrol.config.Constants;
import pl.marcin.michalek.remotecontrol.network.ServiceProvider;
import pl.marcin.michalek.remotecontrol.preferences.Prefs;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Fragment enabling user to enter and store his IP address.
 */
public class EnterIpFragment extends Fragment {

    @Bind(R.id.etServersIp)
    EditText serversIp;

    @Bind(R.id.etServersPort)
    EditText serversPort;

    @Bind(R.id.llLastUsed)
    LinearLayout lastUsedIpLayout;

    @Bind(R.id.btnLastUsedIp)
    Button lasedUsedIp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_ip, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    private void initViews() {
        if (!Prefs.hasLastUsedIp(getActivity())) {
            lastUsedIpLayout.setVisibility(View.GONE);
        } else {
            lastUsedIpLayout.setVisibility(View.VISIBLE);
            lasedUsedIp.setText(Prefs.getLastUsedIp(getActivity()));
            lasedUsedIp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    serversIp.setText(lasedUsedIp.getText().toString());
                }
            });
        }
    }

    private void configureRetrofit() {
        String baseUrl =
            "http://" + serversIp.getText().toString() + ":" + serversPort.getText().toString();
        ServiceProvider.buildRetrofit(baseUrl);
    }

    @OnClick(R.id.btnNext)
    void showControlsFragmentIfServerVersionEqualsAppVersion() {
        configureRetrofit();

        ServiceProvider.provideServerVersionService()
            .getServerVersion().enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Response<Integer> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    if (response.body() == BuildConfig.VERSION_CODE) {
                        showControlsFragment();
                    } else if (response.body() < BuildConfig.VERSION_CODE) {
                        Toast.makeText(getActivity(), "Update application from Google Play",
                                       Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getActivity(), "Download the latest version of server.",
                                       Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(Constants.LOG_TAG, "Error in getting server version: " + t.getMessage());
            }
        });
    }

    private void showControlsFragment() {
        Prefs.putLastUsedIp(getActivity(), serversIp.getText().toString());
        ((MainActivity) getActivity()).replaceFragment(new KeyboardFragment());
    }
}
