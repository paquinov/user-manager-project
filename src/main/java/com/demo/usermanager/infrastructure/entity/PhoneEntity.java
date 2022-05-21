package com.demo.usermanager.infrastructure.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PHONES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneEntity {

    @Id
    @Column(name = "ID", length = 36)
    private String id;

    @Column(name = "NUMBER", length = 9, nullable = false)
    private String number;

    @Column(name = "CITY_CODE", length = 6, nullable = false)
    private String cityCode;

    @Column(name = "COUNTRY_CODE", length = 6, nullable = false)
    private String countryCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "PERSON_ID")
    private PersonEntity person;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive;

    @Column(name = "CREATED_DATE", nullable = false)
    private Date createdDate;

}
