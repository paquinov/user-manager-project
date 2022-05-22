package com.demo.usermanager.infrastructure.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "PERSONS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonEntity {

    @Id
    @Column(name = "ID", length = 36)
    private String id;

    @Column(name = "NAME", length = 100, nullable = false)
    private String name;

    @OneToOne(mappedBy = "person")
    private UserEntity user;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "person")
    private Set<PhoneEntity> phoneEntitySet;

    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @Column(name = "LAST_UPDATE_DATE", nullable = false)
    private Date lastUpdateDate;

}
