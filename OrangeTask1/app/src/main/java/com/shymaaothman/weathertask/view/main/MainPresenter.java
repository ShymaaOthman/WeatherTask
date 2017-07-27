package com.shymaaothman.weathertask.view.main;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shymaaothman.weathertask.FireAuthApplication;

import net.derohimat.baseapp.presenter.BasePresenter;

import com.shymaaothman.weathertask.data.WeatherRepository;
import com.shymaaothman.weathertask.data.local.PreferencesHelper;
import com.shymaaothman.weathertask.data.remote.models.WeatherData;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainPresenter implements BasePresenter<MainView> {

    //weather?q=Cairo&appid=278217c88ac93746ae43b950e3d4b085/

    public final String CITY = "Cairo";
    public final String APPID = "278217c88ac93746ae43b950e3d4b085";


    @Inject
    MainPresenter(Context context) {
        ((FireAuthApplication) context.getApplicationContext()).getApplicationComponent().inject(this);
    }

    @Inject
    WeatherRepository mWatherRepository ;

    @Inject
    FirebaseAuth mAuth;
    @Inject
    PreferencesHelper mPreferencesHelper;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private MainView mView;

//  private Subscription mSubscription;

    @Override
    public void attachView(MainView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
//        if (mSubscription != null) mSubscription.unsubscribe();
    }

    void getUserData() {
        mView.showProgress();

        mAuthListener = firebaseAuth -> {
            mView.hideProgress();
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                mView.getUserData(user);
                Timber.d("user signed in", user.getEmail());
            } else {
                Timber.d("user signed out");
            }
        };
    }


    void getWeatherData(String city,String appId){

        mView.showProgress();
         Observable<WeatherData> weatherObs =  mWatherRepository.getWeatherData(city,appId);

        weatherObs.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weatherData -> {
                    mView.showWeatherData(weatherData);
                });


    }

    void doLogout(){
        mAuth.signOut();
        mPreferencesHelper.clear();
    }

    void removeAuthListener() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    void addAuthListener() {
        if (mAuthListener != null) {
            mAuth.addAuthStateListener(mAuthListener);
        }
    }
}
