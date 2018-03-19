package com.company.project.common.model;

/**
 * 作者: lijun
 * 时间: 2018/3/12 11:05
 * 邮箱: lijun@eims.com.cn
 * 描述：雷达图数据模型
 */
public class RadarData {

    public String title;

    public double percent1=1;
    public double percent2;


    public void initData(int position){
        title=String.format("第%d组数据",position);
        percent2=Math.random();

    }


}
