package com.example.denisdemin.testmts.mainFragment;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.denisdemin.testmts.db.AppDatabase;
import com.example.denisdemin.testmts.db.Model.Cow;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Interactor implements MainFragmentInterface.Interactor {
    private AppDatabase db;
    private MainFragmentInterface.Presenter mPresenter;

    public Interactor(MainFragmentInterface.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void getDBData(Context context) {
        db = Room.databaseBuilder(context,AppDatabase.class,AppDatabase.DB_NAME).build();

        Observable<List<Cow>> o = Observable.fromCallable(new Callable<List<Cow>>() {
            @Override
            public List<Cow> call() throws Exception {
                return db.getCowDao().getAllCows();
            }
        });

        o.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Cow>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Cow> cows) {
                        mPresenter.onSuccess(cows);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
