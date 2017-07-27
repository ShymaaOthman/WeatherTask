package com.shymaaothman.weathertask.view.login;

import com.shymaaothman.weathertask.view.MvpView;


interface LoginMvpView extends MvpView {

    void showProgress();

    void hideProgress();

    void initFbLoginButton();

    void initTwitterLoginButton();
}
