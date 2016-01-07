package it.max.android.domusalberti.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

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

        final String URLArduinoServer = internetUtils.creaURLArduinoServer();

        View view = inflater.inflate(R.layout.fragment_zero, container, false);

        final Context context = view.getContext();

        Switch swcControlloTermosifoni = (Switch) view.findViewById(R.id.swcControlloTermosifoni);

        boolean swcControlloTermosifoniChecked = false;
        if (internetUtils.getResponse(URLArduinoServer + "RelayState").equals("1")) {
            swcControlloTermosifoniChecked = true;
        }

        swcControlloTermosifoni.setChecked(swcControlloTermosifoniChecked);

        swcControlloTermosifoni.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    String URL;

                    if (isChecked) {
                        URL = URLArduinoServer + "Relay=ON";
                        Log.d("URL ContrTermosifoni 1", URL);
//                        URL = internetUtils.getResponse(URL);
                    } else {
                        URL = URLArduinoServer + "Relay=OFF";
                        Log.d("URL ContrTermosifoni 0", URL);
//                        URL = internetUtils.getResponse(URL);
                    }
                } catch (Exception e) {
                    Toast.makeText(context, "ERRORE CAMBIO CONTROLLO MANUALE (MAIN ACTIVITY)!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        container.removeView(view);
        // Inflate the layout for this fragment
        return view;
    }
}
