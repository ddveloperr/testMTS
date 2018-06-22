package com.example.denisdemin.testmts.mainFragment;

import android.content.Context;

import com.example.denisdemin.testmts.db.Model.Cow;

import java.util.List;

public class Presenter implements MainFragmentInterface.Presenter {

    private MainFragmentInterface.View mView;
    private Interactor mInteractor;

    public Presenter(MainFragmentInterface.View mView) {
        this.mView = mView;
        mInteractor = new Interactor(this);
    }

    @Override
    public void getData(Context context) {
        mInteractor.getDBData(context);
    }

    @Override
    public void onSuccess(List<Cow> list) {
        mView.loadingDone(list);
    }
}
