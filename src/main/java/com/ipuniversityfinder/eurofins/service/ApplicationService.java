package com.ipuniversityfinder.eurofins.service;

import com.ipuniversityfinder.eurofins.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationService {
    //Improvements:
    //Could do a separate client for each endpoint
    //Could build the query with some kind of custom class
    @Autowired
    private RestClient client;

    public String getCountryDetails(String ip){
        return client.get(String.format("https://ipinfo.io/%s/geo",ip));
    }

    public String getUniversities(String country){
        return client.get(String.format("http://universities.hipolabs.com/search?country=%s",country));
    }
}
