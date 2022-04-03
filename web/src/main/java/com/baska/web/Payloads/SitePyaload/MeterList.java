package com.baska.web.Payloads.SitePyaload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeterList {

    String serverId;
    String serverName;
    List<MeterList> meterListList;

}
