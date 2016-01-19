package kr.co.kimjubong.android.design.support7;

import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static DrawerLayout mDrawerLayout;
    private final long FINSH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                menuItem.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        if(viewPager !=null){
            setupGirlsViewPager(viewPager);
        }
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Window window = getWindow();
            Transition exitTrans = new Explode();
            //Transition exitTrans = new Fade();
            //Transition exitTrans = new Slide();

            Transition reenterTrans = new Explode();
            //Transition reenterTrans = new Fade();
            //Transition reenterTrans = new Slide();

            window.setExitTransition(exitTrans);
            window.setReenterTransition(reenterTrans);
            //window.setTransitionBackgroundFadeDuration(2000);
            window.setAllowEnterTransitionOverlap(true);
            window.setAllowReturnTransitionOverlap(true);
        }
    }

    private void setupGirlsViewPager(ViewPager viewPager){
        GirlGroupPagerAdapter girlsAdapter = new GirlGroupPagerAdapter(getSupportFragmentManager());
        girlsAdapter.appendFragment(GirlGroupFragment.newInstance(1),"Girls 1");
        girlsAdapter.appendFragment(GirlGroupFragment.newInstance(2),"Girls 2");
        girlsAdapter.appendFragment(GirlGroupFragment.newInstance(1),"Girls 3");
        viewPager.setAdapter(girlsAdapter);
    }

    private static class GirlGroupPagerAdapter extends FragmentPagerAdapter {
        private final ArrayList<GirlGroupFragment> girsFragment = new ArrayList<GirlGroupFragment>();
        private final ArrayList<String> tabTitles = new ArrayList<String>();

        public GirlGroupPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void appendFragment(GirlGroupFragment fragment, String title) {
            girsFragment.add(fragment);
            tabTitles.add(title);
        }

        public Fragment getItem(int position) {
            return girsFragment.get(position);
        }

        public int getCount() {
            return girsFragment.size();
        }

        public CharSequence getPageTitle(int position) {
            return tabTitles.get(position);
        }
    }
        public boolean onOptionsItemSelected(MenuItem item){
            switch(item.getItemId()){
                case android.R.id.home:
                    mDrawerLayout.openDrawer(GravityCompat.START);
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }
        @Override
    public void onBackPressed(){
        long currentTime = System.currentTimeMillis();
        long intervalTime = currentTime - backPressedTime;

        if(0 <= intervalTime && FINSH_INTERVAL_TIME >= intervalTime){
            super.onBackPressed();
        } else{
            backPressedTime = currentTime;
            Toast.makeText(getApplicationContext(),"'뒤로' 버튼 한번 더 누르시면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }
    }
}
