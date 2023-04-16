package com.ipuniversityfinder.eurofins.controller;


import com.ipuniversityfinder.eurofins.service.ApplicationService;
import com.ipuniversityfinder.eurofins.validation.ParamValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Slf4j
@RequestMapping("/api")
public class ApplicationController {
    private static final String COUNTRY_ENDPOINT = "/countries";
    private static final String UNIVERSITIES_ENDPOINT = "/universities";

    @Autowired
    private ParamValidator validator;

    @Autowired
    private ApplicationService service;

    @GetMapping(COUNTRY_ENDPOINT)
    @ResponseBody
    public String getCountryInfo(@RequestParam String ip){
        if(!validator.validateIp(ip)){
            return "Bad IP";
        }
        return service.getCountryDetails(ip);
    }

    @GetMapping(UNIVERSITIES_ENDPOINT)
    @ResponseBody
    public String getUniversities(@RequestParam String country){
        if(!validator.validateCountry(country)){
            return "Bad Country";
        }
        return service.getUniversities(country);

    }
}
