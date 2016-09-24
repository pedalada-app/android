package com.pedalada.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.pedalada.app.MyApplication;
import com.pedalada.app.R;
import com.pedalada.app.fragments.CompetitionFragment;
import com.pedalada.app.fragments.FormHistoryFragment;
import com.pedalada.app.model.Prefs;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.main_navview)
    NavigationView navigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_drawer)
    DrawerLayout drawerLayout;

    private ActionBarDrawerToggle drawerToggle;

    public static void start(Context context) {

        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final Prefs prefs = MyApplication.get(this).getPrefs();
        if (!prefs.isLoggedin()) {
            LoginActivity.start(this);
            finish();
            return;
        }

        setContentView(R.layout.screen_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.main_content, CompetitionFragment.newInstance())
                                   .commit();


        initDrawer();

    }

    private void initDrawer() {

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerOpened(View drawerView) {

                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            public void onDrawerClosed(View view) {

                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }
        };

        drawerLayout.addDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        navigationView.setNavigationItemSelectedListener(item -> {

            selectDrawerItem(item);

            return true;
        });
    }

    private void selectDrawerItem(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_home:
                showFragment(CompetitionFragment.newInstance(), true);
                break;
            case R.id.nav_forms:
                showFragment(FormHistoryFragment.newInstance(), true);
                break;
            default:
                throw new IllegalStateException();
        }

        item.setChecked(true);

        drawerLayout.closeDrawers();
    }

    public void showFragment(Fragment fragment, boolean clearBackstack) {

        if (clearBackstack) {
            clearBackstack();
        }

        final FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction()
                              .replace(R.id.main_content, fragment)
                              .addToBackStack(null)
                              .commit();

    }

    private void clearBackstack() {

        final int backStackEntryCount = getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < backStackEntryCount; i++) {
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {

        super.onPostCreate(savedInstanceState, persistentState);
        drawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
