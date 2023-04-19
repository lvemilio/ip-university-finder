package com.ipuniversityfinder.eurofins.dbrecords;

import jakarta.persistence.*;

@Entity
@Table
public class AuditRecord {

    @Id
    @SequenceGenerator(
            name="record_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    public Long getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getLoc() {
        return loc;
    }

    private String ip;
    private String country;
    private String city;
    private String loc;

    public AuditRecord() {
    }

    @Override
    public String toString() {
        return "AuditRecord{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }

    public AuditRecord(String ip, String country, String city, String loc) {
        this.ip = ip;
        this.country = country;
        this.city = city;
        this.loc = loc;
    }
}
