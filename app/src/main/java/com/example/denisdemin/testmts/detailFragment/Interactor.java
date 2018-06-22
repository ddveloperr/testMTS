package com.example.denisdemin.testmts.detailFragment;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
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

public class Interactor implements DetailFragmentInterface.Interactor {

    private DetailFragmentInterface.Presenter mPresenter;

    public Interactor(DetailFragmentInterface.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void getDBData(final Context context, final int id) {


        Observable<Cow> o = Observable.fromCallable(new Callable<Cow>() {
            @Override
            public Cow call() throws Exception {
                AppDatabase db = Room.databaseBuilder(context,AppDatabase.class,AppDatabase.DB_NAME).build();
                return db.getCowDao().getCow(id);
            }
        });

        o.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Cow>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Cow cow) {
                        mPresenter.onSuccess(cow);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void saveDbData(Context context, Cow cow) {
        AppDatabase db = Room.databaseBuilder(context,AppDatabase.class,AppDatabase.DB_NAME).allowMainThreadQueries().build();

        db.getCowDao().update(cow);

        mPresenter.savingDone(cow.getName());
    }
}
