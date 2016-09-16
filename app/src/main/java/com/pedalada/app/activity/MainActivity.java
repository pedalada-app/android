package com.pedalada.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.pedalada.app.R;
import com.pedalada.app.model.objects.User;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private static final String EXTRA_USER = "user";

    @BindView(R.id.main_name)
    TextView name;

    public static void start(Context context, User user) {

        Intent starter = new Intent(context, MainActivity.class);
        starter.putExtra(EXTRA_USER, user);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final User user = getIntent().getExtras().getParcelable(EXTRA_USER);
        name.setText(user.getName());

    }


}
