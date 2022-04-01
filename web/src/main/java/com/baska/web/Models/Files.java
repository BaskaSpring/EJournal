package com.baska.web.Models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;


@Entity
@Table(name = "files")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Files {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name =  "id", columnDefinition = "VARCHAR(255)",updatable = false,nullable = false)
    private String id;
    private String name;
    private Instant date;
    private String path;
    private Boolean enable;
    private Instant createDate;

    private String serverId;

    @PrePersist
    private void init(){
        createDate = Instant.now();
    }
}
