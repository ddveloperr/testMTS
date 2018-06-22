package com.example.denisdemin.testmts.detailFragment;

import android.content.Context;

import com.example.denisdemin.testmts.db.Model.Cow;

import java.util.List;

public interface DetailFragmentInterface {
    interface View{
        void loadData();
        void loadingDone(Cow cow);
        void saveData(Context context,Cow cow);
        void savingDone(String name);
    }
    interface Presenter {
        void getData(Context context,int id);
        void onSuccess(Cow cow);
        void saveData(Context context,Cow cow);
        void savingDone(String name);
    }
    interface Interactor{
        void getDBData(Context context,int id);
        void saveDbData(Context context,Cow cow);
    }
}

