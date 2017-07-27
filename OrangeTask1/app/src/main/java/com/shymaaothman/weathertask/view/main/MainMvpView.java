package com.shymaaothman.weathertask.view.main;

import com.google.firebase.auth.FirebaseUser;
import com.shymaaothman.weathertask.view.MvpView;


interface MainMvpView extends MvpView {

    void showProgress();

    void hideProgress();

}
