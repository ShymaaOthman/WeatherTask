package com.shymaaothman.weathertask.view.forgot;

import android.app.Activity;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;

import net.derohimat.baseapp.presenter.BasePresenter;
import com.shymaaothman.weathertask.FireAuthApplication;
import com.shymaaothman.weathertask.events.ForgotEvent;
import com.shymaaothman.weathertask.events.RegisterEvent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import timber.log.Timber;


public class ForgotPresenter implements BasePresenter<ForgotView> {

    @Inject
    ForgotPresenter(Context context) {
        ((FireAuthApplication) context.getApplicationContext()).getApplicationComponent().inject(this);
    }

    @Inject
    EventBus mEventBus;
    @Inject
    FirebaseAuth mFirebase;

    private ForgotView mView;
//    private Subscription mSubscription;

    @Override
    public void attachView(ForgotView view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
//        if (mSubscription != null) mSubscription.unsubscribe();
    }

    void resetPassword(Context context, String email) {
        mView.showProgress();
        mFirebase.sendPasswordResetEmail(email)
                .addOnCompleteListener((Activity) context, task -> {
                    mView.hideProgress();

                    if (!task.isSuccessful()) {
                        mEventBus.post(new RegisterEvent(false, task.getException().getMessage()));
                        Timber.e("Unsuccessfully Reset Password : " + task.getException().getMessage());
                    } else {
                        mEventBus.post(new ForgotEvent(true, "Reset password successfully, Please check your email"));
                        Timber.e("Reset password successfully, Please check your email");
                    }
                });
    }

}
