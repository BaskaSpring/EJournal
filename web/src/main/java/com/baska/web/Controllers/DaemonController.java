package com.baska.web.Controllers;

import com.baska.web.Models.ServerRefreshTime;
import com.baska.web.Models.ServerScanPath;
import com.baska.web.Models.Service;
import com.baska.web.Payloads.DaemonPayload.Computer;
import com.baska.web.Payloads.DaemonPayload.FilesPayload;
import com.baska.web.Payloads.DaemonPayload.PathPayload;
import com.baska.web.Repository.ServerMeterRepository;
import com.baska.web.Repository.ServerRefreshTimeRepository;
import com.baska.web.Repository.ServerScanPathsRepository;
import com.baska.web.Service.CRUDService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/apid/v1")
@RestController
public class DaemonController {


    @Autowired
    ServerRefreshTimeRepository serverRefreshTimeRepository;

    @Autowired
    ServerMeterRepository serverMeterRepository;

    @Autowired
    ServerScanPathsRepository serverScanPathsRepository;

    @Autowired
    CRUDService crudService;

    @PostMapping("/discspace")
    public ResponseEntity<?> add(@Validated @RequestBody Computer computer){
        Optional<ServerRefreshTime> server = serverRefreshTimeRepository._findByServerName(computer.getName(), Service.DiskSpace.toString());
        if (server.isEmpty()) {
            return ResponseEntity.ok("0");
        }
        ServerRefreshTime serverRefreshTime =server.get();

        crudService.CreateServerMeter(serverRefreshTime.getServerId(),computer.getDiscArrayList());

        return ResponseEntity.ok(serverRefreshTime.getRefreshTime().toString());
    }

    @PostMapping("/files")
    public ResponseEntity<?> addFiles(@Validated @RequestBody FilesPayload filesPayload){
        crudService.CreateFiles(filesPayload);
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/files")
    public ResponseEntity<?> getFiles(@RequestParam String computerName){
        Optional<ServerRefreshTime> serverRefreshTime = serverRefreshTimeRepository._findByServerName(computerName, Service.ScanPath.toString());
        if (serverRefreshTime.isEmpty()) {
            return ResponseEntity.ok("0");
        }
        List<ServerScanPath> server = serverScanPathsRepository._findByServerName(computerName);
        if (server.size()==0) {
            return ResponseEntity.ok("0");
        }
        List<String> paths = new ArrayList<>();
        for (ServerScanPath el: server) {
            paths.add(el.getPath());
        }
        PathPayload pathPayload = new PathPayload();
        pathPayload.setPath(paths);
        pathPayload.setRefreshTime(serverRefreshTime.get().getRefreshTime());
        Gson gson = new Gson();
        String json = gson.toJson(pathPayload);
        return ResponseEntity.ok(json);
    }
}
