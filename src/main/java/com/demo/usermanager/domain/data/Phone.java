package com.demo.usermanager.domain.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class Phone {

    private String id;
    private String number;
    private String cityCode;
    private String countryCode;
    private boolean isActive;
    private Date createdDate;

}
