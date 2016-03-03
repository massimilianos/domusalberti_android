package it.max.android.domusalberti.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import it.max.android.domusalberti.R;
import it.max.android.domusalberti.entity.Stanza;
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
/*
        Switch swcControlloTermosifoni = (Switch) view.findViewById(R.id.swcControlloTermosifoni);

        boolean swcControlloTermosifoniChecked = false;
//        if (internetUtils.getResponse(URLArduinoServer + "RelayState").equals("1")) {
//            swcControlloTermosifoniChecked = true;
//        }

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
*/
        Stanza[] stanze = {
                            new Stanza("Ufficio", 22, 33, R.drawable.temperature, R.drawable.humidity),
                            new Stanza("Uno", 21, 34, R.drawable.temperature, R.drawable.humidity),
                            new Stanza("Tre", 20, 35, R.drawable.temperature, R.drawable.humidity),
                            new Stanza("Quattro", 23, 32, R.drawable.temperature, R.drawable.humidity),
                            new Stanza("Cinque", 24, 31, R.drawable.temperature, R.drawable.humidity),
                            new Stanza("Sei", 19, 30, R.drawable.temperature, R.drawable.humidity)
                          };
        final ArrayList<Stanza> listaStanze = new ArrayList<Stanza>();
        for (int i = 0; i < stanze.length; ++i) {
            listaStanze.add(stanze[i]);
        }

        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();

        for(int i = 0; i < listaStanze.size(); i++) {
            Stanza s = listaStanze.get(i);

            HashMap<String,Object> stanzaMap = new HashMap<String, Object>();

            stanzaMap.put("nomeStanza", s.getNomeStanza());
            stanzaMap.put("temperatura", s.getTemperatura() + "°");
            stanzaMap.put("imgTemperatura", s.getImgTemperatura());
            stanzaMap.put("umidita", s.getUmidita() + "%");
            stanzaMap.put("imgUmidita", s.getImgUmidita());
            data.add(stanzaMap);
        }

        String[] from = { "nomeStanza","temperatura","imgTemperatura","umidita","imgUmidita" };
        int[] to = {R.id.nomeStanza, R.id.temperatura, R.id.imgTemperatura, R.id.umidita, R.id.imgUmidita};

        SimpleAdapter adapter=new SimpleAdapter (
                context,
                data,
                R.layout.lista_stanze,
                from,
                to);

        ((ListView)view.findViewById(R.id.listaStanze)).setAdapter(adapter);

        container.removeView(view);
        // Inflate the layout for this fragment
        return view;
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {
        HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
            for (int i = 0; i < objects.size(); ++i) {
                mIdMap.put(objects.get(i), i);
            }
        }

        @Override
        public long getItemId(int position) {
            String item = getItem(position);
            return mIdMap.get(item);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
