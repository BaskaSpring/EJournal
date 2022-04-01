package com.baska.web.Service;


import com.baska.web.Models.Bases;
import com.baska.web.Models.Files;
import com.baska.web.Models.ServerMeter;
import com.baska.web.Models.Servers;
import com.baska.web.Payload.Disc;
import com.baska.web.Payload.FilesPayload;
import com.baska.web.Payload._Files;
import com.baska.web.Repository.BasesRepository;
import com.baska.web.Repository.FilesRepository;
import com.baska.web.Repository.ServerMeterRepository;
import com.baska.web.Repository.ServersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
public class CRUDService {

    @Autowired
    ServerMeterRepository serverMeterRepository;

    @Autowired
    FilesRepository filesRepository;

    @Autowired
    ServersRepository serversRepository;

    @Autowired
    BasesRepository basesRepository;

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
        Optional<Servers> server1 = serversRepository.getServer(filesPayload.getServerName());
        if (server1.isEmpty()){
            return;
        }
        Servers server = server1.get();

        List<Files> enabledFiles = filesRepository.getEnableFiles();
        List<_Files> newFiles = filesPayload.getFilesList();
        for (_Files file :newFiles){
            for (Files enablefile: enabledFiles){
                if (file.getFileName().equals(enablefile.getName())){
                    enabledFiles.remove(enablefile);
                    newFiles.remove(file);
                }
            }
        }
        for (Files files:enabledFiles){
            files.setEnable(false);
            filesRepository.save(files);
        }
        List<Bases> bases = basesRepository.getAll();
        for (_Files files: newFiles){
            Files newFile = new Files();
            newFile.setDate(files.getCreateDate());
            newFile.setName(files.getFileName());
            newFile.setServerId(server.getId());
            newFile.setBasesId(FindBase(files.getFileName(),bases));
            filesRepository.save(newFile);
        }
    }


    public String FindBase(String fileName, List<Bases> bases){
        for(Bases base: bases){
            if (fileName.contains(base.getUniqString())){

            }
        }
        return "";
    }

}
