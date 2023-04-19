package com.ipuniversityfinder.eurofins.service;

import com.ipuniversityfinder.eurofins.client.RestClient;
import com.ipuniversityfinder.eurofins.dbrecords.AuditRecord;
import com.ipuniversityfinder.eurofins.errors.Errors;
import com.ipuniversityfinder.eurofins.repository.AuditRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.json.HTTP;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
@Slf4j
public class ApplicationService {
    //Improvements:
    //Could do a separate client for each endpoint
    //Could build the query with some kind of custom class
    private final AuditRecordRepository repository;
    private RestClient client;

    @Autowired
    public ApplicationService(AuditRecordRepository repository, RestClient client) {
        this.repository = repository;
        this.client = client;
    }


    public ResponseEntity<List<AuditRecord>> getAuditRecords(){
        return new ResponseEntity<List<AuditRecord>>(repository.findAll(),HttpStatus.OK);
    }

    public ResponseEntity<String> getCountryDetails(String ip){
        JSONObject response = new JSONObject(client.get(String.format("https://ipinfo.io/%s/geo",ip)));
        if(response.has("bogon")){
            return new ResponseEntity<>(Errors.PRIVATE_IP_ERROR,HttpStatus.NOT_FOUND);
        }
        AuditRecord record = createAuditRecord(response);
        log.info("Saving audit record: {}",record.toString());
        repository.save(record);
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    public ResponseEntity<String> getUniversities(String country){
        String response = client.get(String.format("http://universities.hipolabs.com/search?country=%s",country));
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    private AuditRecord createAuditRecord(JSONObject res){
        return new AuditRecord(res.getString("ip"),res.getString("country"),
                res.getString("city"),res.getString("loc"));
    }

}
