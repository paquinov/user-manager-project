package com.demo.usermanager.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneRequest {

    private String number;
    private String cityCode;
    private String countryCode;

}
