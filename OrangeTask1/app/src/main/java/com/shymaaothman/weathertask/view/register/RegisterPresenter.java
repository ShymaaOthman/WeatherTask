package com.shymaaothman.weathertask.view.register;

import android.app.Activity;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.shymaaothman.weathertask.FireAuthApplication;

import net.derohimat.baseapp.presenter.BasePresenter;

import com.shymaaothman.weathertask.events.RegisterEvent;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import timber.log.Timber;


public class RegisterPresenter implements BasePresenter<RegisterView> {

    @Inject
    RegisterPresenter(Context context) {
        ((FireAuthApplication) context.getApplicationContext()).getApplicationComponent().inject(this);
    }

    @Inject
    FirebaseAuth mAuth;
    @Inject
    EventBus mEventBus;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private RegisterView mView;
//    private Subscription mSubscription;

    @Override
    public void attachView(RegisterView view) {
        mView = view;
        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Timber.d("user signed in", user.getDisplayName());
            } else {
                Timber.d("user signed out");
            }
        };
    }

    @Override
    public void detachView() {
        mView = null;
//        if (mSubscription != null) mSubscription.unsubscribe();
    }

    void doRegister(Context context, String email, String password) {
        mView.showProgress();
//        if (mSubscription != null) mSubscription.unsubscribe();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener((Activity) context, task -> {
                    mView.hideProgress();
                    if (!task.isSuccessful()) {
                        mEventBus.post(new RegisterEvent(false, task.getException().getMessage()));
                        Timber.e("Unsuccessfully Register : " + task.getException().getMessage());
                    } else {
                        mEventBus.post(new RegisterEvent(true, "Successfully created user"));
                        Timber.d("Successfully created user account with uid: " + task.getResult().getUser().getUid());
                    }
                });
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
