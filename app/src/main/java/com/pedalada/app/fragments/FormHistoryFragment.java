package com.pedalada.app.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.collect.Lists;
import com.pedalada.app.MyApplication;
import com.pedalada.app.R;
import com.pedalada.app.activity.MainActivity;
import com.pedalada.app.adapter.FormHistoryAdapter;
import com.pedalada.app.model.network.BackendService;
import com.pedalada.app.model.objects.UserForm;
import com.pedalada.app.presenter.FormHistoryPresenter;
import com.pedalada.app.view.FormHistoryView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;
import timber.log.Timber;

public class FormHistoryFragment extends BaseFragment implements FormHistoryView, FormHistoryAdapter.FormHistoryAdapterListener {


    @BindView(R.id.form_history_list)
    RecyclerView formHistoryList;

    private FormHistoryPresenter presenter;

    private PublishSubject<UserForm> userFormPublishSubject = PublishSubject.create();

    private FormHistoryAdapter adapter;

    public static FormHistoryFragment newInstance() {

        Bundle args = new Bundle();

        FormHistoryFragment fragment = new FormHistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        BackendService backendService = MyApplication.get(getContext()).getBackendService();

        presenter = new FormHistoryPresenter(backendService);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.screen_form_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        adapter = new FormHistoryAdapter(getContext(), Lists.newArrayList(), this);
        formHistoryList.setAdapter(adapter);
        formHistoryList.setLayoutManager(new LinearLayoutManager(getContext()));

        presenter.attachView(this);
    }

    @Override
    public void showForms(List<UserForm> userForms) {

        Timber.d("showForms() + %s", userForms);

        adapter.addForms(userForms);

        getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
    }

    @Override
    public Observable<UserForm> onFormClicked() {

        return userFormPublishSubject;
    }

    @Override
    public void showForm(String id) {

        final FormFragment formFragment = FormFragment.newInstance(id);

        ((MainActivity) getActivity()).showFragment(formFragment, false);

    }

    @Override
    public void onFormHistoryClicked(UserForm userForm) {

        userFormPublishSubject.onNext(userForm);

    }
}
