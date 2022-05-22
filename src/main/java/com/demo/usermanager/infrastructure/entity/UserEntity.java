package com.demo.usermanager.infrastructure.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @Column(name = "ID", length = 36)
    private String id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "TOKEN", nullable = false)
    private String token;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PERSON_ID", referencedColumnName = "ID")
    private PersonEntity person;

    @Column(name = "LAST_LOGIN_DATE", nullable = false)
    private Date lastLoginDate;

    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

    @Column(name = "LAST_UPDATE_DATE", nullable = false)
    private Date lastUpdateDate;

}
