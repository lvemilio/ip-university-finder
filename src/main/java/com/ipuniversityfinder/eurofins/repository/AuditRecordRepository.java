package com.ipuniversityfinder.eurofins.repository;

import com.ipuniversityfinder.eurofins.dbrecords.AuditRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRecordRepository extends JpaRepository<AuditRecord,Long> {

}
