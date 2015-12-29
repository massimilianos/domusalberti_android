package it.max.android.domusalberti.fragments;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import it.max.android.domusalberti.R;
import it.max.android.domusalberti.utils.InternetUtils;

public class ZeroFragment extends Fragment {
    private InternetUtils internetUtils;

    public ZeroFragment() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String arduinoAddress = getArguments().getString("arduinoAddress");
        String arduinoPort = getArguments().getString("arduinoPort");

        internetUtils = new InternetUtils(arduinoAddress, arduinoPort);

        View view = inflater.inflate(R.layout.fragment_zero, container, false);

        TextView txtOne = (TextView) view.findViewById(R.id.txt_fragment_zero);
        txtOne.setText("TEMP.: '" +
                       internetUtils.getResponse(internetUtils.creaURLArduinoServer() + "TemperatureRead") + "'");

        container.removeView(view);
        // Inflate the layout for this fragment
        return view;
    }
}
