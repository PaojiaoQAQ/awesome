package com.example.demo.service.impl;

import com.example.demo.service.DailyService;
import com.example.demo.utils.HttpClientUtils;
import com.example.demo.utils.ResourceUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
@Service
public class DailyserviceImpl implements DailyService {
    @Override
    public String getWeatherInfo(String cityDm) {
        String uri = ResourceUtil.getAppconfigByKey("weather.uri") + cityDm;
        String respData = HttpClientUtils.doGet(uri,new HashMap<>());
        return respData;
    }
}
