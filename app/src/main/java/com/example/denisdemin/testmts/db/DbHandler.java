package com.example.denisdemin.testmts.db;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.example.denisdemin.testmts.db.Model.Cow;


import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class DbHandler {

    private AppDatabase db;
    private Context context;

    public DbHandler(Context context) {
        this.context = context;

        db = Room.databaseBuilder(context,AppDatabase.class,AppDatabase.DB_NAME).build();
    }

//    public void insertSingle(Cow cow){
//        final Cow insert = cow;
//
//        Completable.fromAction(new Action() {
//            @Override
//            public void run() throws Exception {
//                db.getCowDao().insertSingle(insert);
//            }
//        }).observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
//            @Override
//            public void onSubscribe(Disposable d) {
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d("Database","inserted");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d("Database","error Insert"+e.toString());
//            }
//        });
//    }

    public void insertList(List<Cow> cow){
        final List<Cow> list = cow;
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.getCowDao().insertList(list);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("Database","inserted list");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Database","inserted list error"+e.toString());
                    }
                });
    }

    public void update(Cow cow){
        final Cow update = cow;
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                db.getCowDao().update(update);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("Database","updated");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Database","updated error"+e.toString());
                    }
                });
    }

//    public List<Cow> getCowList(){
//        Observable<List<Cow>> o = db.getCowDao().getAllCows();
//        List<Cow> returnList;
//
//        o.observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Observer<List<Cow>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(List<Cow> cows) {
//                        returnList = cows;
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }
}
