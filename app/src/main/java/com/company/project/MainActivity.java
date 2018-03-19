package com.company.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {



    }


    @OnClick({R.id.radarChartBtn, R.id.barChartBtn, R.id.compineCharBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.radarChartBtn:
                //雷达图
                RadarChartActivity.go(this);
                break;
            case R.id.barChartBtn:
                //柱状图
                break;
            case R.id.compineCharBtn:
                //折线图
                break;
        }
    }
}
