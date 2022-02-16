package com.example.demo.cos.config;

import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.common.profile.Language;
import com.tencentcloudapi.cvm.v20170312.CvmClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @description
 * @author tanyue
 * @Date 2022/2/15
 **/
@Configuration
public class CvmConfig
{
    @Value("${cos.common.access_id}")
    private String secretId;

    @Value("${cos.common.access_key}")
    private String secretKey;
    @Value("${cos.common.region}")
    private String region;
    @Bean
    public CvmClient commonCvmClient(){
        Credential cred = new Credential(secretId, secretKey);
        CvmClient client = new CvmClient(cred,region);
        ClientProfile clientProfile = new ClientProfile();
        clientProfile.setLanguage(Language.ZH_CN);
        clientProfile.setDebug(true);
        HttpProfile httpProfile = new HttpProfile();
//        httpProfile.setProtocol("http://"); //http 协议
        httpProfile.setProtocol("https://"); //https 协议
        clientProfile.setHttpProfile(httpProfile);
        client.setClientProfile(clientProfile);
        return client;
    }

}
