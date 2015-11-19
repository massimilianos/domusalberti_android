package it.max.android.domusalberti;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import it.max.android.domusalberti.fragments.*;
import it.max.android.domusalberti.utils.InternetUtils;

public class MainActivity extends AppCompatActivity {
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.isInEditMode();
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(), MainActivity.this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);
    }
*/
    private Context context = null;
    private Resources resources = null;
    private AssetManager assetManager = null;
    private Properties properties = null;

    private Bundle bundle = null;

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            context = getApplicationContext();

            resources = this.getResources();
            assetManager = resources.getAssets();

            InputStream inputStream = assetManager.open("domusalberti.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch(Exception e) {
            Toast.makeText(context, "ERRORE LETTURA FILE PROPERTIES (MAIN ACTIVITY)!!!", Toast.LENGTH_SHORT).show();
            System.exit(-1);
        }

        bundle = new Bundle();
        bundle.putString("webserverAddress", properties.getProperty("webserverAddress"));
        bundle.putString("webserverPort", properties.getProperty("webserverPort"));
        bundle.putString("webserverSitePath", properties.getProperty("webserverSitePath"));
        bundle.putString("arduinoAddress", properties.getProperty("arduinoAddress"));
        bundle.putString("arduinoPort", properties.getProperty("arduinoPort"));

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    private void setupTabIcons() {
        int[] tabIcons = {
                R.drawable.num_1,
                R.drawable.num_2,
                R.drawable.num_3,
                R.drawable.num_4,
                R.drawable.num_5
        };

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }

    private void setupViewPager(ViewPager viewPager) {
        OneFragment oneFragment = new OneFragment();
        oneFragment.setArguments(bundle);

        TwoFragment twoFragment = new TwoFragment();
        twoFragment.setArguments(bundle);

        ThreeFragment threeFragment = new ThreeFragment();
        threeFragment.setArguments(bundle);

        FourFragment fourFragment = new FourFragment();
        fourFragment.setArguments(bundle);

        FiveFragment fiveFragment = new FiveFragment();
        fiveFragment.setArguments(bundle);

        SixFragment sixFragment = new SixFragment();
        sixFragment.setArguments(bundle);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(oneFragment, "UNO");
        adapter.addFrag(twoFragment, "DUE");
        adapter.addFrag(threeFragment, "TRE");
        adapter.addFrag(fourFragment, "QUATTRO");
        adapter.addFrag(fiveFragment, "CINQUE");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // return null to display only the icon
            return null;
        }
    }
}