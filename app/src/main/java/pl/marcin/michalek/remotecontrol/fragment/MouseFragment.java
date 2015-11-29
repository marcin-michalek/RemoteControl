package pl.marcin.michalek.remotecontrol.fragment;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import pl.marcin.michalek.remotecontrol.R;
import pl.marcin.michalek.remotecontrol.activity.MainActivity;
import pl.marcin.michalek.remotecontrol.util.PointUtil;

import butterknife.ButterKnife;
import butterknife.OnTouch;

/**
 * First skeleton of MouseFragment. Will be cleaned and improved in next commit.
 */
public class MouseFragment extends Fragment implements View.OnTouchListener {

    Point down = null;
    Point up = null;

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
            down = new Point((int) event.getX(), (int) event.getY());
        } else if (MotionEvent.ACTION_UP == event.getActionMasked()) {
            up = new Point((int) event.getX(), (int) event.getY());
            Toast
                .makeText(getActivity(),
                          "Distance: " + PointUtil.distance(down, up) + " Angle: " + PointUtil
                              .angleInDegrees(down, up),
                          Toast.LENGTH_SHORT).show();
        }
        return true;
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
}
