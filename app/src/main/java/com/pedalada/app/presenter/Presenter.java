package com.pedalada.app.presenter;

import com.pedalada.app.view.BaseView;

public interface Presenter<T extends BaseView> {


    void attachView(T view);

    void detachView();

}
