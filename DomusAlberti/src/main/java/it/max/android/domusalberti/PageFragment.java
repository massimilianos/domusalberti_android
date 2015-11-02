package it.max.android.domusalberti;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.codeandmagic.android.gauge.GaugeView;

import java.util.Random;

// In this case, the fragment displays simple text based on the page
public class PageFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private GaugeView mGaugeView;
    private final Random RAND = new Random();

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
        mGaugeView = (GaugeView) view.findViewById(R.id.gauge_view);
        mGaugeView.setTargetValue(RAND.nextInt(101));
        return view;
    }
}