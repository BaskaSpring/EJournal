package com.baska.web.Models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "refreshtime")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServerRefreshTime {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name =  "id", columnDefinition = "VARCHAR(255)",updatable = false,nullable = false)
    private String id;

    private Integer refreshTime;

    private String service;

    private String serverId;

}
