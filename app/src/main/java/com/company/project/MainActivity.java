package com.company.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.radarView)
    RadarView radarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView(){
        List<RadarData> dataList=new ArrayList<>();
        for(int i=0;i<6;i++){
            RadarData radarData=new RadarData();
            radarData.initData();
            dataList.add(radarData);
        }


        radarView.setData(dataList);




    }


}
