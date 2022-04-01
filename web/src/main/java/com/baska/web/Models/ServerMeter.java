package com.baska.web.Models;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "meters")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerMeter {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name =  "id", columnDefinition = "VARCHAR(255)",updatable = false,nullable = false)
    private String id;
    private Instant timestamp;
    private String discName;
    private Long freeSpace;
    private Long totalSpace;

    private String serverId;

    @PrePersist
    private void init(){
        timestamp = Instant.now();
    }

}
