package com.ipuniversityfinder.eurofins.controller;


import com.ipuniversityfinder.eurofins.dbrecords.AuditRecord;
import com.ipuniversityfinder.eurofins.errors.Errors;
import com.ipuniversityfinder.eurofins.service.ApplicationService;
import com.ipuniversityfinder.eurofins.validation.ParamValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@Slf4j
@RequestMapping("/api")
public class ApplicationController {
    private static final String COUNTRY_ENDPOINT = "/countries";
    private static final String UNIVERSITIES_ENDPOINT = "/universities";
    private static final String RECORD_ENDPOINT = "/records";

    @Autowired
    private ParamValidator validator;

    @Autowired
    private ApplicationService service;

    //Improvements:
    //It would be best to have some kind of custom class for returning responses

    @GetMapping(COUNTRY_ENDPOINT)
    @ResponseBody
    public ResponseEntity<String> getCountryInfo(@RequestParam String ip){
        if(!validator.validateIp(ip)){
            //Would probably create some kind of custom Error object for these cases
            return new ResponseEntity<String>(Errors.IP_ERROR, HttpStatus.BAD_REQUEST);
        }
        return service.getCountryDetails(ip);
    }

    @GetMapping(UNIVERSITIES_ENDPOINT)
    @ResponseBody
    public ResponseEntity<String> getUniversities(@RequestParam String country){
        if(!validator.validateCountry(country)){
            return new ResponseEntity<String>(Errors.COUNTRY_ERROR, HttpStatus.BAD_REQUEST);
        }
        return service.getUniversities(country);
    }

    @GetMapping(RECORD_ENDPOINT)
    @ResponseBody
    public ResponseEntity<List<AuditRecord>> getAuditRecords(){
        return service.getAuditRecords();
    }
}
