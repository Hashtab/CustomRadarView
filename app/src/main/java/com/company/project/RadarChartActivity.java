package com.company.project;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.company.project.common.model.RadarData;
import com.company.project.common.view.RadarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者: lijun
 * 时间: 2018/3/15 15:40
 * 邮箱: lijun@eims.com.cn
 * 描述：雷达图显示界面
 */
public class RadarChartActivity extends AppCompatActivity {
    @Bind(R.id.radarView)
    RadarView radarView;

    public static void go(Context context) {
        Intent starter = new Intent(context, RadarChartActivity.class);
        context.startActivity(starter);
    }
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.radarchart_activity);
        ButterKnife.bind(this);
        initView();

    }


    private void initView(){
        List<RadarData> dataList=new ArrayList<>();
        for(int i=0;i<6;i++){
            RadarData radarData=new RadarData();
            radarData.initData(i);
            dataList.add(radarData);
        }
        
        radarView.setData(dataList);
    }


}
