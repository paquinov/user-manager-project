package com.demo.usermanager.domain.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class Person {

    private String id;
    private String name;
    private Date createdDate;
    private Date lastUpdateDate;
    private List<Phone> phoneList;

}
