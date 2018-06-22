package com.example.denisdemin.testmts.detailFragment;

import android.content.Context;

import com.example.denisdemin.testmts.db.Model.Cow;

public class Presenter implements DetailFragmentInterface.Presenter {
    private DetailFragmentInterface.View mView;
    private Interactor mInteractor;

    public Presenter(DetailFragmentInterface.View mView) {
        this.mView = mView;
        mInteractor = new Interactor(this);
    }

    @Override
    public void getData(Context context, int id) {
        mInteractor.getDBData(context, id);
    }

    @Override
    public void onSuccess(Cow cow) {
        mView.loadingDone(cow);
    }

    @Override
    public void saveData(Context context,Cow cow) {
        mInteractor.saveDbData(context,cow);
    }

    @Override
    public void savingDone(String name) {
        mView.savingDone(name);
    }
}
