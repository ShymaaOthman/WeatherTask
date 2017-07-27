package com.shymaaothman.weathertask.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.shymaaothman.weathertask.data.remote.models.WeatherData;
import com.shymaaothman.weathertask.view.FireAuthBaseActivity;
import com.shymaaothman.weathertask.view.login.LoginActivity;
import com.shymaaothman.weathertask.R ;
import com.shymaaothman.weathertask.util.DialogFactory;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends FireAuthBaseActivity implements MainView {

    @Bind(R.id.inpEmail)
    EditText mInpEmail;
    @Bind(R.id.temp_txt)
     TextView temp_txt ;
    @Bind(R.id.maxtemp_txt)
    TextView maxtemp_txt ;
    @Bind(R.id.mintemp_txt)
    TextView mintemp_txt ;
    @Bind(R.id.desc_txt)
    TextView desc_txt ;

    private MainPresenter mPresenter;
    ProgressBar mProgressBar = null;

    //weather?q=Cairo&appid=278217c88ac93746ae43b950e3d4b085/

    public final String CITY = "Cairo";
    public final String APPID = "278217c88ac93746ae43b950e3d4b085";


    @Override
    protected int getResourceLayout() {
        return R.layout.main_activity;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        mPresenter = new MainPresenter(this);
        mPresenter.attachView(this);

        getBaseActionBar().setElevation(0);
        getBaseActionBar().hide();

        mPresenter.getUserData();

        mPresenter.getWeatherData(CITY,APPID);;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        if (mProgressBar == null) {
            mProgressBar = DialogFactory.DProgressBar(mContext);
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void getUserData(FirebaseUser firebaseUser) {
        mInpEmail.setText(firebaseUser.getEmail());
    }

    @Override
    public void showWeatherData(WeatherData weatherData) {

        double temp = round(weatherData.getMain().getTemp()-273 ,1) ;
        double maxtemp = round(weatherData.getMain().getTempMax()-273 ,1) ;
        double mintemp = round(weatherData.getMain().getTempMin()-273 ,1) ;

        temp_txt.setText(temp + " C");
        maxtemp_txt.setText("Max  : " +maxtemp+ " C");
        mintemp_txt.setText("Min  : " +mintemp+ " C");
        desc_txt.setText(weatherData.getWeather().get(0).getDescription());

        Log.e("weather data",weatherData.getWeather().get(0).getDescription());
    }

    private  double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.addAuthListener();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.removeAuthListener();
    }

    @OnClick(R.id.btnLogout)
    void logoutCLick() {
        mPresenter.doLogout();
        LoginActivity.start(mContext);
        finish();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    public Context getContext() {
        return this;
    }
}
