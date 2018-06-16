package com.example.omnia.ta3ala_2ma_2a2olk_client.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.omnia.ta3ala_2ma_2a2olk_client.Interfaces.Search;
import com.example.omnia.ta3ala_2ma_2a2olk_client.R;
import com.example.omnia.ta3ala_2ma_2a2olk_client.SharredPreference.SharredPreferenceManager;
import com.example.omnia.ta3ala_2ma_2a2olk_client.adapters.NewsFeedsAdapter;
import com.example.omnia.ta3ala_2ma_2a2olk_client.model.NewsFeed;
import com.example.omnia.ta3ala_2ma_2a2olk_client.presenter.SearchPresenter;
import com.example.omnia.ta3ala_2ma_2a2olk_client.rest.ApiClient;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    MaterialSearchView searchView;
    MenuItem logout;
    private RecyclerView recyclerView;
    private NewsFeedsAdapter adapter;

    Toolbar toolbar;
    // SearchPresenter presenter;
    //abdalla start
    FloatingActionButton fabBtn;

    //abdalla end
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        //  presenter = new SearchPresenter(this);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
//abdalla start
        fabBtn = (FloatingActionButton) findViewById(R.id.fabBtn);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

                switch (position) {
                    case 0:
                        fabBtn.show();
                        break;

                    default:
                        fabBtn.hide();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
//abdalla end

  //abdalla start
        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences userDetails = getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = userDetails.edit();
                SharredPreferenceManager manager = new SharredPreferenceManager(getApplicationContext());
                String email = manager.getString(userDetails, "email", "no");
                // Toast.makeText(getApplicationContext(), "email is " + email, Toast.LENGTH_LONG).show();
                Log.e("prefMail",email);
                if (email.equals("no")) {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    //abdalla start
                } else if(ApiClient.isNetworkAvalaiable()){
                    Intent intent = new Intent(getApplicationContext(), AddQuestion.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else if(!ApiClient.isNetworkAvalaiable()){
                    Toast.makeText(getApplicationContext(), "no network connection Avalaiabl", Toast.LENGTH_LONG).show();

                }
            }
        });
   //abdalla end
        //abdalla start


        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(mViewPager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.slideDotActive);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.slideDotdisActive);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );


        //abdalla end

        searchViewCode();
    }


    //abdalla start
    public FloatingActionButton getFloatingActionButton() {
        return fabBtn;
    }

    //abdalla end
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView view = (SearchView) menu.findItem(R.id.action_search).getActionView();
        if (null != view) {
            view.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            view.setIconifiedByDefault(false);
        }
        view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // presenter.loadSearchResults(s,getApplicationContext(),MainActivity.this);
                 Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
//                Bundle bundle = new Bundle();
//                bundle.putString("query", s);
//// set Fragmentclass Arguments
//                Tab1NewsFeeds fragobj = new Tab1NewsFeeds();
//                fragobj.setArguments(bundle);
//                FragmentManager fragmentManager = getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.container,fragobj);
//                fragmentTransaction.commit();
                Intent i = new Intent(MainActivity.this, SearchActivity.class);
                i.putExtra("query", s);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        logout = menu.findItem(R.id.action_logout);
        SharedPreferences userDetails = getSharedPreferences("LoginPref", Context.MODE_PRIVATE);
        SharredPreferenceManager manager = new SharredPreferenceManager(getApplicationContext());
        String id = manager.getString(userDetails, "id", "0");
        if (id.equals("0")) {
            logout.setVisible(false);
        }

        searchView.setMenuItem(item);
        MenuItem profile = menu.findItem(R.id.action_profile);
        profile.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent myIntent = new Intent(MainActivity.this, ProfileScreen.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplication().startActivity(myIntent);
                return false;
            }
        });
        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                SharedPreferences preferences = getSharedPreferences("LoginPref", 0);
                SharredPreferenceManager manager = new SharredPreferenceManager(getApplicationContext());
                manager.remove(preferences, "email");
                manager.remove(preferences, "id");
                Toast.makeText(getApplicationContext(), "Log out Successful", Toast.LENGTH_LONG).show();
                Intent myIntent = new Intent(MainActivity.this, MainActivity.class);
                myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplication().startActivity(myIntent);

                return false;
            }
        });
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_search:
                searchView.setVisibility(View.VISIBLE);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
        //Ahmed Hesham
        finish();
    }

//    @Override
//    public void showSearchResults(List<NewsFeed> data) {
////        adapter = new NewsFeedsAdapter(data, MainActivity.this);
////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
////        recyclerView.setLayoutManager(layoutManager);
////        recyclerView.setAdapter(adapter);
//        Bundle bundle = new Bundle();
//        bundle.putArra("edttext", "From Activity");
//// set Fragmentclass Arguments
//        Tab1NewsFeeds fragobj = new Tab1NewsFeeds();
//        fragobj.setArguments(bundle);
//    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // return the current tab

            switch (position) {
                case 0:
                    Tab1NewsFeeds tab1 = new Tab1NewsFeeds();
                    return tab1;
                case 1:
                    Tab2CustomerService tab2 = new Tab2CustomerService();
                    return tab2;
                case 2:
                    Tab3Categories tab3 = new Tab3Categories();
                    return tab3;
                case 3:
                    Tab4Places tab4 = new Tab4Places();
                    return tab4;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }
    }

    private void searchViewCode() {
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setSuggestions(getResources().getStringArray(R.array.query_suggestions));
        searchView.setEllipsize(true);
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_SHORT).show();
                Log.e("search", query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
//abdalla start

                searchView.setVisibility(View.VISIBLE);
//abdalla end
            }

            @Override
            public void onSearchViewClosed() {

            }
        });
        searchView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }    /*click alt+insert key */

}
