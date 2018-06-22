package com.example.denisdemin.testmts.mainFragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.denisdemin.testmts.R;
import com.example.denisdemin.testmts.db.Model.Cow;
import com.example.denisdemin.testmts.detailFragment.DetailFragment;
import com.example.denisdemin.testmts.mainActivity.MainActivity;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<Cow> list;

    public MainRecyclerAdapter(Context context, List<Cow> list) {
        this.context = context;
        this.list = list;
    }

    public class HeaderViewHolder extends RecyclerView.ViewHolder{

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.nameField)
        TextView nameField;
        @BindView(R.id.breedField)
        TextView breed;
        @BindView(R.id.colorField)
        TextView colorField;
        @BindView(R.id.ageField)
        TextView ageField;
        @BindView(R.id.rootItem)
        RelativeLayout rootItem;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==1) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_item,parent,false);
            return new MainViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recycler_header,parent,false);
            return new HeaderViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==1) {
            MainViewHolder mainViewHolder = (MainViewHolder) holder;
            position--;

            mainViewHolder.nameField.setText(list.get(position).getName());
            mainViewHolder.breed.setText(list.get(position).getBreed());
            mainViewHolder.colorField.setText(list.get(position).getColor());
            mainViewHolder.ageField.setText(String.valueOf(list.get(position).getAge()));

            final int finalPosition = position;
            mainViewHolder.rootItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DetailFragment detailFragment = new DetailFragment();
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", list.get(finalPosition).getId());
                    detailFragment.setArguments(bundle);

                    ((MainActivity)context).setFragment(detailFragment);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size()+1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return 0;
        }else{
            return 1;
        }
    }
}
