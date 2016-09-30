package com.pedalada.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.common.collect.Lists;
import com.pedalada.app.MyApplication;
import com.pedalada.app.R;
import com.pedalada.app.adapter.FormSummaryAdapter;
import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.objects.UserForm;
import com.pedalada.app.presenter.FormPresenter;
import com.pedalada.app.view.FormView;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class FormFragment extends BaseFragment implements FormView {

    private static final String EXTRA_FORM_ID = "formId";

    @BindView(R.id.form_fixtures_list)
    RecyclerView formFixturesList;

    @BindView(R.id.form_date)
    TextView formDateTv;

    @BindView(R.id.form_status)
    TextView formStatusTv;

    @BindView(R.id.form_expected_revenue)
    TextView formExpectedRevenue;

    @BindView(R.id.form_pedalada)
    TextView formPedaladaTv;

    private FormPresenter presenter;

    private FormSummaryAdapter adapter;

    public static FormFragment newInstance(String formId) {

        Bundle args = new Bundle();
        args.putString(EXTRA_FORM_ID, formId);

        FormFragment fragment = new FormFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final BackendService backendService = MyApplication.get(getContext()).getBackendService();

        presenter = new FormPresenter(backendService);
        presenter.setForm(getArguments().getString(EXTRA_FORM_ID));

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.screen_form, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        presenter.attachView(this);

        adapter = new FormSummaryAdapter(getContext(), Lists.newArrayList());
        formFixturesList.setAdapter(adapter);
        formFixturesList.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    @Override
    public void showForm(UserForm userForm) {

        Timber.d("showForm() userForm=[%s]", userForm);

        getActivity().runOnUiThread(() -> {
            formDateTv.setText(userForm.getName());
            formStatusTv.setText(String.format("Status: %s", userForm.getStatus()));
            formExpectedRevenue.setText(String.format("Expected Revenue: %d", userForm.getExpectedWinning()));
            formPedaladaTv.setText(String.format("Pedalada form: %d", userForm.getPedaladas()));

            adapter.showList(userForm.getBets());

        });
    }
}