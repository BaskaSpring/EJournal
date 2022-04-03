package com.baska.web.Payloads.SitePyaload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Meters {

    Instant timestamp;
    String discName;
    Long freeSpace;
    Long totalSpace;

}
