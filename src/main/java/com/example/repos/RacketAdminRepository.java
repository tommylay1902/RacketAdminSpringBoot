package com.example.repos;

import com.example.entities.RacketAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RacketAdminRepository extends JpaRepository<RacketAdmin, Integer> {

}
