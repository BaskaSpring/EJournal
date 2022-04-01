package com.baska.web.Service;


import com.baska.web.Models.Files;
import com.baska.web.Models.ServerMeter;
import com.baska.web.Payload.Disc;
import com.baska.web.Payload.FilesPayload;
import com.baska.web.Payload._Files;
import com.baska.web.Repository.FilesRepository;
import com.baska.web.Repository.ServerMeterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CRUDService {

    @Autowired
    ServerMeterRepository serverMeterRepository;

    @Autowired
    FilesRepository filesRepository;

    public void CreateServerMeter(String serverId, List<Disc> discList) {
        for (Disc el : discList) {
            ServerMeter serverMeter = new ServerMeter();
            serverMeter.setServerId(serverId);
            serverMeter.setFreeSpace(Long.parseLong(el.getFreeSpace()));
            serverMeter.setTotalSpace(Long.parseLong(el.getTotalSpace()));
            serverMeter.setDiscName(el.getName());
            serverMeterRepository.save(serverMeter);
        }
    }


    public void CreateFiles(FilesPayload filesPayload){
        List<Files> enabledFiles = filesRepository.getEnableFiles();
        for (_Files file :filesPayload.getFilesList()){
            for (Files enablefile: enabledFiles){
                if (file.getFileName().equals(enablefile.getName())){

                }
            }
        }
    }

}
