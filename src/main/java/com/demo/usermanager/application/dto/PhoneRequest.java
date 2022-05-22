package com.demo.usermanager.application.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneRequest {

    @ApiModelProperty(notes = "Phone number", position = 1, example = "1234567")
    private String number;
    @ApiModelProperty(notes = "Phone city code", position = 2, example = "01")
    private String cityCode;
    @ApiModelProperty(notes = "Phone country code", position = 3, example = "51")
    private String countryCode;

}
