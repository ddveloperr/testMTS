package com.example.denisdemin.testmts.mainFragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.denisdemin.testmts.db.Model.Cow;

import java.util.List;

public interface MainFragmentInterface {
    interface View{
        void loadData();
        void loadingDone(List<Cow> list);
    }
    interface Presenter {
        void getData(Context context);
        void onSuccess(List<Cow> list);

    }
    interface Interactor{
        void getDBData(Context context);
    }
}
