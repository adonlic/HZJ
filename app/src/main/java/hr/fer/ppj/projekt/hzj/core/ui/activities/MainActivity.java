package hr.fer.ppj.projekt.hzj.core.ui.activities;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.camera.ui.CameraActivity;
import hr.fer.ppj.projekt.hzj.core.adapters.ViewPagerAdapter;
import hr.fer.ppj.projekt.hzj.core.cache.QuizzesCache;
import hr.fer.ppj.projekt.hzj.core.cache.UserCache;
import hr.fer.ppj.projekt.hzj.core.helpers.QuizHelper;
import hr.fer.ppj.projekt.hzj.core.repositories.implementations.HZJContext;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.FavoritesFragment;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.QuizzesFragment;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.SectionsFragment;

public class MainActivity extends AppCompatActivity {
    // navigation drawer and toggle button
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    // main content stuff
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;

    ViewPagerAdapter viewPagerAdapter;

    FloatingActionButton fab;

    // navigation drawer stuff
    ImageView imageView;

    // RETROFIT (CONSUMING WEB API) - we need it here for loading user's data
    HZJService HZJService;
    HZJContext dataContext;

    SectionsFragment sections;
    QuizzesFragment quizzes;
    FavoritesFragment favorites;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RETROFIT
        setupAPI();
        // CACHE-TO SERVER CONNECTION
        setupDataStorage();

        // main content stuff
        setupToolbar();
        setupViewPager();
        setupTabs();
        //setupSlidingTab();

        /* TO FIX "BUG", as we can't switch ViewPager/Tabs initialization order,
         * we put data on fragments ("tabs", in real meaning we put data on view pager)
         * after setting up tabs!
         */
        // bindDataToFragments();d

        setupFloatingActionButton();

        // navigation drawer stuff
        setupNavigationDrawer();
        setupNavigationDrawerContent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent settings = new Intent(this, SettingsActivity.class);
                startActivity(settings);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        // setting custom toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("HRVATSKI ZNAKOVNI JEZIK");
        if (UserCache.getUser() == null)
            toolbar.setSubtitle("Ulogirani ste kao: gost");
        else
            toolbar.setSubtitle("Ulogirani ste kao: " + UserCache.getUser().getUsername());
        // bind custom toolbar to this activity
        setSupportActionBar(toolbar);
        // getSupportActionBar().setHomeButtonEnabled(true);
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        // showing navigation drawer icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupTabs() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        int[] tabIcons = {
                R.drawable.ic_school,
                R.drawable.ic_question_answer,
                R.drawable.ic_favorite
        };

        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(
                        getBaseContext(), R.color.colorPrimaryDark);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

                // we overrided this methods to add fancy icon tinting, now we have to fix selection
                viewPager.setCurrentItem(tab.getPosition(), true);
                // tabLayout.getSelectedTabPosition(), true....
                // Toast.makeText(getBaseContext(), "TAB ID = " + tabLayout.getSelectedTabPosition(), Toast.LENGTH_LONG).show();   // TOAST¨!!!!!
                // Log.i("TAB " + tab.getText(), "onTabSelected()");

                // if to fill with data, fill...
                /*
                if (tab.getPosition() == 0)
                    sections.fillWithData();
                else if (tab.getPosition() == 1)
                    quizzes.fillWithData();
                else if (tab.getPosition() == 2)
                    favorites.fillWithData();
                    */
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabIconColor = ContextCompat.getColor(
                        getBaseContext(), R.color.colorTabUnselected);
                tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                // Log.i("TAB " + tab.getText(), "onTabUnselected()");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
                Log.i("TAB " + tab.getText(), "onTabReselected()");
            }
        });
        // correction of selected tab icon color bug when activity is opened
        int tabIconColor = ContextCompat.getColor(
                getBaseContext(), R.color.colorPrimaryDark);
        tabLayout.getTabAt(0).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        /*
        int tabIconColor = ContextCompat.getColor(
                getBaseContext(), R.color.colorPrimaryDark);
        tabLayout.getTabAt(0).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(1).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        tabLayout.getTabAt(2).getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
        */

        //viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //tabLayout.setTabsFromPagerAdapter(viewPagerAdapter);
    }

    private void setupViewPager() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        // setting reference to data storage
        sections = new SectionsFragment();
        quizzes = new QuizzesFragment();
        favorites = new FavoritesFragment();

        // bind fragments to adapter that'll control positioning and so...
        viewPagerAdapter.addFragment(sections, "Nauči me");
        viewPagerAdapter.addFragment(quizzes, "Kvizovi");
        viewPagerAdapter.addFragment(favorites, "Favoriti");

        viewPager.setAdapter(viewPagerAdapter);

        // viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }


    private void setupFloatingActionButton() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera = new Intent(getBaseContext(), CameraActivity.class);
                startActivity(camera);
            }
        });
    }

    private void setupNavigationDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        ) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });
    }

    private void setupNavigationDrawerContent() {
        imageView = (ImageView) findViewById(R.id.profile_picture);

        int imageIconColor = ContextCompat.getColor(
                getBaseContext(), R.color.colorPrimaryDark);
        imageView.setColorFilter(imageIconColor, PorterDuff.Mode.SRC_IN);

        // setup basic user details (info)
        TextView nameAndSurname = (TextView) findViewById(R.id.name_and_surname);
        TextView username = (TextView) findViewById(R.id.username);
        if (UserCache.getUser() != null) {
            nameAndSurname.setText(UserCache.getUser().getName() + " "
                    + UserCache.getUser().getSurname());
            username.setText(UserCache.getUser().getUsername());
        }
    }

    private void setupAPI() {
        HZJService.setupAPI();
    }

    private void setupDataStorage() {
        this.dataContext = new HZJContext(this);
    }

    /*
    private void setupSlidingTab() {
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Nauči me"));
        tabLayout.addTab(tabLayout.newTab().setText("Kvizovi"));
        tabLayout.addTab(tabLayout.newTab().setText("Favoriti"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        SectionsFragment sections = new SectionsFragment();
        QuizzesFragment quizzes = new QuizzesFragment();
        FavoritesFragment favorites = new FavoritesFragment();
        sections.dataContextReference(dataContext);
        quizzes.dataContextReference(dataContext);
        favorites.dataContextReference(dataContext);
        viewPagerAdapter.addFragment(sections, "Nauči me");
        viewPagerAdapter.addFragment(quizzes, "Kvizovi");
        viewPagerAdapter.addFragment(favorites, "Favoriti");
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    */
}
