package com.baska.web.Sheduler;

import com.baska.web.Models.ServerMeter;
import com.baska.web.Repository.ServerMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.List;

@Configuration
public class Cleaner {

    @Autowired
    ServerMeterRepository serverMeterRepository;

    @Scheduled( cron = "0 10 1 * * *")
    public void cleanServerMeter(){
       // List<ServerMeter> serverMeterList = serverMeterRepository.getServerMeterList(Instant.now());
       // serverMeterList.forEach(x->serverMeterRepository.delete(x));
    }
}
