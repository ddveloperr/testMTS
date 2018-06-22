package com.example.denisdemin.testmts.mainFragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.denisdemin.testmts.R;
import com.example.denisdemin.testmts.db.Model.Cow;
import com.example.denisdemin.testmts.mainActivity.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements MainFragmentInterface.View {

    @BindView(R.id.mainRecycler)
    RecyclerView recyclerView;

    Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main,container,false);
        ButterKnife.bind(this,v);

        mPresenter = new Presenter(this);

        loadData();

        return v;
    }

    @Override
    public void loadData() {
        mPresenter.getData(getContext());
    }

    @Override
    public void loadingDone(List<Cow> list) {
        MainRecyclerAdapter mainRecyclerAdapter = new MainRecyclerAdapter(getContext(),list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mainRecyclerAdapter);
    }
}
