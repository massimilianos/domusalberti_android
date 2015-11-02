package it.max.android.domusalberti;

import android.app.DownloadManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.codeandmagic.android.gauge.GaugeView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

// In this case, the fragment displays simple text based on the page
public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private GaugeView mGaugeViewTemperature;
    private GaugeView mGaugeViewHumidity;
    private final Random RAND = new Random();

    private String leggiResponse (InputStream response) {
        BufferedReader r = new BufferedReader(new InputStreamReader(response));
        StringBuilder total = new StringBuilder();
        String line;

        try {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
        } catch(Exception e) {
        }

        return(total.toString());
    }

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);

//        TextView textView = (TextView) view;
//        textView.setText("Fragment #" + mPage);

        mGaugeViewTemperature = (GaugeView) view.findViewById(R.id.gauge_view_temperature);
        mGaugeViewHumidity = (GaugeView) view.findViewById(R.id.gauge_view_humidity);

//        mGaugeViewTemperature.setTargetValue(RAND.nextInt(50));
//        mGaugeViewHumidity.setTargetValue(20 + RAND.nextInt(60));

        String response = new String();
        try {
            HttpURLConnection con = (HttpURLConnection) ( new URL("http://massimilianos.ns0.it:82/index.htm?TemperatureRead")).openConnection();
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            InputStream is = con.getInputStream();
            response = this.leggiResponse(is);
            con.disconnect();

        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("TEMPERATURA 1: 'ERRORE'");
        }

        System.out.println("TEMPERATURA 2: '" + response + "'");

        Integer temperature = 0;
        if (response.matches("^-?\\d+$"))
            temperature = Integer.parseInt(response);

        System.out.println("TEMPERATURA 3: '" + temperature + "'");

        mGaugeViewTemperature.setTargetValue(temperature);

        return view;
    }
}