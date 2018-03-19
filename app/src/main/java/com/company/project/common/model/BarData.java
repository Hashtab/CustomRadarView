package com.company.project.common.model;

/**
 * 作者: lijun
 * 时间: 2018/3/16 11:37
 * 邮箱: lijun@eims.com.cn
 * 描述：柱状图数据
 */
public class BarData {

    public String title;

    public double percent1=1;
    public double percent2;


    public void initData(int position){
        title=String.format("第%d组数据",position);
        percent2=Math.random();

    }

}
