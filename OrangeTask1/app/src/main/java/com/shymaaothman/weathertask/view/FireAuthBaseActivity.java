package com.shymaaothman.weathertask.view;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.shymaaothman.weathertask.FireAuthApplication;
import com.shymaaothman.weathertask.di.component.ActivityComponent;
import com.shymaaothman.weathertask.di.component.DaggerActivityComponent;

import net.derohimat.baseapp.ui.BaseActivity;

import butterknife.ButterKnife;
import timber.log.Timber;

public abstract class FireAuthBaseActivity extends BaseActivity {

    private ActivityComponent mComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        ButterKnife.bind(this);
        Timber.tag(getClass().getSimpleName());
        mInflater = LayoutInflater.from(mContext);
        mComponent = DaggerActivityComponent.builder().applicationComponent(getApp().getApplicationComponent()).build();
        onViewReady(savedInstanceState);
    }

    protected ActivityComponent getComponent() {
        return mComponent;
    }

    protected FireAuthApplication getApp() {
        return (FireAuthApplication) getApplicationContext();
    }

}
