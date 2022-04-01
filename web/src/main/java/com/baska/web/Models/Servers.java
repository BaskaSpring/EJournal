package com.baska.web.Models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "servers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Servers {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name =  "id", columnDefinition = "VARCHAR(255)",updatable = false,nullable = false)
    private String id;

    private String serverName;

    private boolean enabled;

}
