package pl.marcin.michalek.remotecontrol.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import pl.marcin.michalek.remotecontrol.R;
import pl.marcin.michalek.remotecontrol.activity.MainActivity;
import pl.marcin.michalek.remotecontrol.network.ServicePaths;
import pl.marcin.michalek.remotecontrol.preferences.Prefs;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @OnClick(R.id.btnNext)
    void saveIpAndGoToControlsFragment() {
        ServicePaths.ROOT_REST_URL =
            "http://" + serversIp.getText().toString() + ":" + serversPort.getText().toString();
        Prefs.putLastUsedIp(getActivity(), serversIp.getText().toString());
        ((MainActivity) getActivity()).replaceFragment(new KeyboardFragment());
    }
}
