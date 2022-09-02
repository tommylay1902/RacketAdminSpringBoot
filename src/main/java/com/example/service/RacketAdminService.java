package com.example.service;

import com.example.entities.Order;
import com.example.entities.RacketAdmin;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface RacketAdminService {
    List<RacketAdmin> getAll();
    ResponseEntity<String> insert(RacketAdmin rd);
    ResponseEntity<String> update(RacketAdmin rd, int id);
    ResponseEntity<String> delete(int id);
    Optional<RacketAdmin> getRacketAdminById(int id);
    ResponseEntity<String> deleteAll();
    List<RacketAdmin> popRAadmin();

    int orderByDay(Date start, Date end);

}
