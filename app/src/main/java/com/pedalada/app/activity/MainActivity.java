package com.pedalada.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.pedalada.app.MyApplication;
import com.pedalada.app.R;
import com.pedalada.app.fragments.CompetitionFragment;
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
            return;
        }

        setContentView(R.layout.screen_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                                   .add(R.id.main_content, CompetitionFragment.newInstance())
                                   .commit();

    }


}
