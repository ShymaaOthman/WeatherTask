package com.shymaaothman.weathertask.view.login;

import net.derohimat.baseapp.view.BaseView;


interface LoginView extends BaseView {

    void showProgress();

    void hideProgress();

    void initFbLoginButton();

    void initTwitterLoginButton();
}
