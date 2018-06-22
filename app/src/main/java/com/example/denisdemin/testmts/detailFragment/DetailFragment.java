package com.example.denisdemin.testmts.detailFragment;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSpinner;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.denisdemin.testmts.R;
import com.example.denisdemin.testmts.db.Model.Cow;
import com.example.denisdemin.testmts.db.Model.Fat;
import com.example.denisdemin.testmts.db.Model.Weight;
import com.example.denisdemin.testmts.util.GraphClass;
import com.example.denisdemin.testmts.util.ValueClass;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.LineData;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment implements DetailFragmentInterface.View,DatePickerDialog.OnDateSetListener {

    private Cow graphCowData;

    private Presenter mPresenter;

    View v;

    @BindView(R.id.nameEditText)
    EditText editText;

    @BindView(R.id.datePickText)
    TextView datePickText;

    @BindView(R.id.motherSpinner)
    AppCompatSpinner motherSpinner;

    @BindView(R.id.fatherSpinner)
    AppCompatSpinner fatherSpinner;

    @BindView(R.id.breedSpinner)
    AppCompatSpinner breedSpinner;

    @BindView(R.id.colorSpinner)
    AppCompatSpinner colorSpinner;

    @BindView(R.id.saveButton)
    Button saveButton;

    @BindView(R.id.addData)
    Button addData;

    @BindView(R.id.chart)
    LineChart chart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_detail,container,false);
        ButterKnife.bind(this,v);
        mPresenter = new Presenter(this);
        loadData();
        return v;
    }

    @Override
    public void loadData() {
        Bundle bundle = getArguments();
        mPresenter.getData(getContext(),bundle.getInt("id"));
    }

    @Override
    public void loadingDone(Cow cow) {
        editText.setText(cow.getName());

        SpannableString string = new SpannableString(cow.getBirthDate());
        string.setSpan(new UnderlineSpan(),0,string.length(),0);
        datePickText.setText(string);

        initSpinners(cow);

        drawGraph(cow);

        final DatePickerDialog picker = new DatePickerDialog(getContext(),this,
                cow.getYear(),cow.getMonthOfYear()-1,cow.getDayOfMonth());

        datePickText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picker.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getArguments();
                Cow cow = new Cow(bundle.getInt("id"),
                        editText.getText().toString(),
                        breedSpinner.getSelectedItem().toString(),
                        colorSpinner.getSelectedItem().toString(),
                        datePickText.getText().toString(),
                        motherSpinner.getSelectedItem().toString(),
                        fatherSpinner.getSelectedItem().toString(),
                        graphCowData.getWeightList(),
                        graphCowData.getFatList());
                saveData(getContext(),cow);
            }
        });

        final Cow finalCow = cow;


        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
                View mView = getLayoutInflater().inflate(R.layout.dialog_layout,null);
                final EditText dateEditText = mView.findViewById(R.id.editDateText);
                final EditText weightEditText = mView.findViewById(R.id.editWeightText);
                final EditText fatEditText = mView.findViewById(R.id.editFatText);
                Button dialogConfirm = mView.findViewById(R.id.dialogConfirm);

                mBuilder.setView(mView);

                final AlertDialog ad = mBuilder.show();

                dialogConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!dateEditText.getText().toString().matches("") && !weightEditText.getText().toString().matches("") && !fatEditText.getText().toString().matches("")){
                            drawGraph(addDataToCow(finalCow,dateEditText.getText().toString()
                                    ,weightEditText.getText().toString()
                                    ,fatEditText.getText().toString()));
                            ad.cancel();
                        }else{
                            Toast.makeText(getContext(), R.string.invalid_input,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void saveData(Context context, Cow cow) {
        mPresenter.saveData(context,cow);
    }

    @Override
    public void savingDone(String name) {
        Snackbar.make(v,getResources().getString(R.string.save_message,name),Snackbar.LENGTH_SHORT).show();
    }

    private int getPreviewIndex(String[] array,String text){
        return Arrays.asList(array).indexOf(text);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        String temp = dayOfMonth+"."+String.valueOf(month+1)+"."+year;
        SpannableString string = new SpannableString(temp);
        string.setSpan(new UnderlineSpan(),0,string.length(),0);
        datePickText.setText(string);
    }


    private void drawGraph(Cow cow){
        graphCowData = cow;
        GraphClass graphClass = new GraphClass(cow);

        LineData lineData = new LineData(graphClass.getDataSets());

        XAxis xAxis = chart.getXAxis();

        xAxis.setValueFormatter(graphClass.getXFormatter());
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(-45f);


        chart.setData(lineData);
        chart.getLegend().setTextSize(16f);
        chart.getLegend().setXEntrySpace(25f);
        chart.getLegend().setYOffset(30f);
        chart.invalidate();
    }

    private Cow addDataToCow(Cow cow,String date,String weight,String fat){
        List<Fat> fatList = cow.getFatList();
        List<Weight> weightList = cow.getWeightList();
        Cow cowl = cow;

        fatList.add(new Fat(Integer.valueOf(fat),date));
        weightList.add(new Weight(Integer.valueOf(weight),date));

        cowl.setFatList(fatList);
        cowl.setWeightList(weightList);
        return cowl;
    }

    private void initSpinners(Cow cow){
        ValueClass valueClass = new ValueClass();

        ArrayAdapter<String> motherArrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,valueClass.motherArray);
        ArrayAdapter<String> fatherArrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,valueClass.fatherArray);
        ArrayAdapter<String> breedArrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,valueClass.breedArray);
        ArrayAdapter<String> colorArrayAdapter = new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,valueClass.colorArray);

        motherSpinner.setAdapter(motherArrayAdapter);
        fatherSpinner.setAdapter(fatherArrayAdapter);
        breedSpinner.setAdapter(breedArrayAdapter);
        colorSpinner.setAdapter(colorArrayAdapter);


        motherSpinner.setSelection(getPreviewIndex(valueClass.motherArray,cow.getMother()));
        fatherSpinner.setSelection(getPreviewIndex(valueClass.fatherArray,cow.getFather()));
        breedSpinner.setSelection(getPreviewIndex(valueClass.breedArray,cow.getBreed()));
        colorSpinner.setSelection(getPreviewIndex(valueClass.colorArray,cow.getColor()));
    }
}
