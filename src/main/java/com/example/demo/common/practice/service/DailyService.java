package com.example.demo.common.practice.service;

public interface DailyService {
    //根据城市代码获取天气信息
    String getWeatherInfo(String cityDm);

    void syncCal();

}
