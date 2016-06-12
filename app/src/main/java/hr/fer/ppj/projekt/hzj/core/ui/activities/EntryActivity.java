package hr.fer.ppj.projekt.hzj.core.ui.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.adapters.ViewPagerAdapter;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.LoginFragment;
import hr.fer.ppj.projekt.hzj.core.ui.fragments.RegisterFragment;

public class EntryActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Dobrodošli u HZJ!");
        setSupportActionBar(toolbar);
        setupSlidingView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_entry, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent settings = new Intent(this, MainActivity.class);
                startActivity(settings);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupSlidingView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.entry_view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        LoginFragment loginFragment = new LoginFragment();
        RegisterFragment registerFragment = new RegisterFragment();

        // we'll use same adapter as in content screen... (MainActivity.class)
        viewPagerAdapter.addFragment(loginFragment, "Ulogiraj se");
        viewPagerAdapter.addFragment(registerFragment, "Registracija");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
