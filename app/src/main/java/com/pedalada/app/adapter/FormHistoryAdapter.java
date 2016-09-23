package com.pedalada.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pedalada.app.R;
import com.pedalada.app.model.objects.UserForm;
import com.pedalada.app.viewholder.FormHistoryViewHolder;

import java.util.List;

public class FormHistoryAdapter extends RecyclerView.Adapter<FormHistoryViewHolder> {

    private final List<UserForm> userFormList;

    private final FormHistoryAdapterListener listener;

    private LayoutInflater layoutInflater;

    public FormHistoryAdapter(Context context, List<UserForm> userFormList, FormHistoryAdapterListener listener) {

        layoutInflater = LayoutInflater.from(context);
        this.userFormList = userFormList;
        this.listener = listener;
    }

    @Override
    public FormHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View view = layoutInflater.inflate(R.layout.item_form, parent, false);
        return new FormHistoryViewHolder(view, listener);

    }

    @Override
    public void onBindViewHolder(FormHistoryViewHolder holder, int position) {

        final UserForm userForm = userFormList.get(position);
        holder.bind(userForm);

    }

    @Override
    public int getItemCount() {

        return userFormList.size();
    }

    public void addForms(List<UserForm> userForms) {

        userFormList.addAll(userForms);

    }

    public interface FormHistoryAdapterListener {

        void onFormHistoryClicked(UserForm userForm);

    }
}
