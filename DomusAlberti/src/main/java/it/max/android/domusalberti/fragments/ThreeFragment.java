package it.max.android.domusalberti.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import it.max.android.domusalberti.R;

public class ThreeFragment extends Fragment {
    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Random rand = new Random();

        View view = inflater.inflate(R.layout.fragment_three, container, false);

        TextView txtRandomThree = (TextView) view.findViewById(R.id.txt_fragment_three);
        txtRandomThree.setText("BIG PIRLA TRE!!!");

        container.removeView(view);
        // Inflate the layout for this fragment
        return view;
    }

}
