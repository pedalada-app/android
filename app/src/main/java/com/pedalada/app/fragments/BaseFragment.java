package com.pedalada.app.fragments;

import android.support.v4.app.Fragment;
import android.view.View;

import com.pedalada.app.R;
import com.pedalada.app.view.BaseView;

import butterknife.BindView;

public abstract class BaseFragment extends Fragment implements BaseView {

    @BindView(R.id.progress_bar)
    View progressBar;

    @Override
    public void showProgress() {

        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {

        progressBar.setVisibility(View.INVISIBLE);

    }
}
