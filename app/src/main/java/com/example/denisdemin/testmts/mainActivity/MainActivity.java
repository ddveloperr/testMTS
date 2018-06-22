package com.example.denisdemin.testmts.mainActivity;

import android.arch.persistence.room.Room;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.amitshekhar.DebugDB;
import com.example.denisdemin.testmts.R;
import com.example.denisdemin.testmts.db.AppDatabase;
import com.example.denisdemin.testmts.db.Model.Cow;
import com.example.denisdemin.testmts.db.Model.Fat;
import com.example.denisdemin.testmts.db.Model.Weight;
import com.example.denisdemin.testmts.mainFragment.MainFragment;
import com.example.denisdemin.testmts.util.PrefHandler;
import com.example.denisdemin.testmts.util.ValueClass;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    PrefHandler prefHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         prefHandler = new PrefHandler(this);

        initActivity();
    }

    private void initActivity(){
        if(prefHandler.getFirstLaunch()){
            loadTestingData();
            prefHandler.setFirstLaunch(false);
        }
        setFragment(new MainFragment());
    }

    public void setFragment(Fragment fragment){
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right,R.anim.slide_out_left,R.anim.slide_in_right)
                .replace(R.id.frameContainer,fragment)
                .addToBackStack(null)
                .commit();
    }

    private void loadTestingData(){

        final AppDatabase db = Room.databaseBuilder(this,AppDatabase.class,AppDatabase.DB_NAME).allowMainThreadQueries().build();
        ValueClass valueClass = new ValueClass();

        Cow cow1 = new Cow(1,valueClass.nameArray[0],valueClass.breedArray[1],valueClass.colorArray[0],"28.02.1996",
                valueClass.motherArray[1],
                valueClass.fatherArray[1],
                valueClass.weightList,
                valueClass.fatList);

        Cow cow2 = new Cow(2,valueClass.nameArray[4],valueClass.breedArray[2],valueClass.colorArray[1],"01.01.1990",
                valueClass.motherArray[0],
                valueClass.fatherArray[1],
                valueClass.weightList,
                valueClass.fatList);

        Cow cow3 = new Cow(3,valueClass.nameArray[3],valueClass.breedArray[3],valueClass.colorArray[2],"05.07.1963",
                valueClass.motherArray[1],
                valueClass.fatherArray[0],
                valueClass.weightList,
                valueClass.fatList);

        Cow cow4 = new Cow(4,valueClass.nameArray[1],valueClass.breedArray[4],valueClass.colorArray[0],"11.09.2001",
                valueClass.motherArray[0],
                valueClass.fatherArray[0],
                valueClass.weightList,
                valueClass.fatList);

        List<Cow> list = new ArrayList<>();

        list.add(cow1);
        list.add(cow2);
        list.add(cow3);
        list.add(cow4);

        db.getCowDao().insertList(list);
    }
}
