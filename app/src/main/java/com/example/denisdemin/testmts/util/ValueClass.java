package com.example.denisdemin.testmts.util;

import com.example.denisdemin.testmts.db.Model.Fat;
import com.example.denisdemin.testmts.db.Model.Weight;

import java.util.ArrayList;
import java.util.List;

public class ValueClass {

    public String[] nameArray = new String[]{"Гоша","Леша","Оля","Маша","Ваня"};

    public String[] breedArray= new String[]{"Голштинская","Пестрая","Холмогорская","Голландская","Ярославская"};

    public String[] colorArray= new String[]{"Ч-Б","Коричневая","Сиреневая"};

    public String[] motherArray = new String[]{"param1","param2"};

    public String[] fatherArray = new String[]{"param3","param4","param5"};

    public List<Fat> fatList = new ArrayList<>();

    public List<Weight> weightList = new ArrayList<>();

    public ValueClass() {

        fatList.add(new Fat(22,"05.05.2018"));
        fatList.add(new Fat(24,"07.05.2018"));
        fatList.add(new Fat(23,"08.05.2018"));
        fatList.add(new Fat(27,"15.05.2018"));
        fatList.add(new Fat(32,"20.05.2018"));
        fatList.add(new Fat(29,"23.05.2018"));

        weightList.add(new Weight(420,"05.05.2018"));
        weightList.add(new Weight(411,"07.05.2018"));
        weightList.add(new Weight(412,"08.05.2018"));
        weightList.add(new Weight(430,"15.05.2018"));
        weightList.add(new Weight(450,"20.05.2018"));
        weightList.add(new Weight(446,"23.05.2018"));

    }
}
