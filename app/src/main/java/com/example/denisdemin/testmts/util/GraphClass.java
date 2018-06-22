package com.example.denisdemin.testmts.util;

import com.example.denisdemin.testmts.R;
import com.example.denisdemin.testmts.db.Model.Cow;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

public class GraphClass {

    private Cow cow;

    public int FAT_DATA_CONST=0;
    public int WEIGHT_DATA_CONTST=1;

    public GraphClass(Cow cow) {
        this.cow = cow;
    }

    public List<ILineDataSet> getDataSets(){
        List<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(getFatData());
        dataSets.add(getWeightData());
        return dataSets;
    }

    public IAxisValueFormatter getXFormatter(){
        return new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return cow.getFatList().get(Math.round(value)).getDate();
            }
        };
    }

    private LineDataSet getFatData(){
        List<Entry> entries = new ArrayList<>();

        for(int i=0;i<cow.getFatList().size();i++){
            entries.add(new Entry(i,cow.getFatList().get(i).getFatValue()));
        }

        LineDataSet dataSet = new LineDataSet(entries,"Жир %");
        dataSet.setLineWidth(4f);
        dataSet.setValueTextSize(12f);
        dataSet.setColor(R.color.colorAccent);

        return dataSet;
    }

    private LineDataSet getWeightData(){
        List<Entry> entries = new ArrayList<>();

        for(int i=0;i<cow.getWeightList().size();i++){
            entries.add(new Entry(i,cow.getWeightList().get(i).getWeightValue()));
        }

        LineDataSet dataSet = new LineDataSet(entries,"Вес кг.");
        dataSet.setLineWidth(4f);
        dataSet.setValueTextSize(12f);
        return dataSet;
    }
}
