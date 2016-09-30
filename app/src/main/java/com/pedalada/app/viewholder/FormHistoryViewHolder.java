package com.pedalada.app.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pedalada.app.R;
import com.pedalada.app.adapter.FormHistoryAdapter;
import com.pedalada.app.model.objects.UserForm;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormHistoryViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.item_form_date)
    TextView dateTextView;

    @BindView(R.id.item_form_pedalada_count)
    TextView pedaladaCountTv;

    @BindView(R.id.item_form_status)
    TextView statusTv;

    private FormHistoryListener listener;

    public FormHistoryViewHolder(View itemView, FormHistoryAdapter.FormHistoryAdapterListener listener) {

        super(itemView);
        ButterKnife.bind(this, itemView);

        this.listener = new FormHistoryListener(listener);
        itemView.setOnClickListener(this.listener);

    }

    public void bind(UserForm userForm) {

        dateTextView.setText(userForm.getName());
        pedaladaCountTv.setText("Pedaladas: " + userForm.getPedaladas());
        statusTv.setText(userForm.getStatus().displayed());

        listener.setUserForm(userForm);
    }

    private static class FormHistoryListener implements View.OnClickListener {

        private FormHistoryAdapter.FormHistoryAdapterListener listener;

        private UserForm userForm;

        private FormHistoryListener(FormHistoryAdapter.FormHistoryAdapterListener listener) {

            this.listener = listener;
        }

        @Override
        public void onClick(View view) {

            listener.onFormHistoryClicked(userForm);

        }

        public void setUserForm(UserForm userForm) {

            this.userForm = userForm;
        }
    }
}
