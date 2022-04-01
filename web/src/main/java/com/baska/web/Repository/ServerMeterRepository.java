package com.baska.web.Repository;

import com.baska.web.Models.ServerMeter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;


@Repository
public interface ServerMeterRepository extends JpaRepository<ServerMeter,String> {
//
//    @Query("SELECT e FROM ServerMeter as e WHERE e.timestamp < :time")
//    Iterable<ServerMeter> getServerMeterList(@Param("time") Instant time);




}
