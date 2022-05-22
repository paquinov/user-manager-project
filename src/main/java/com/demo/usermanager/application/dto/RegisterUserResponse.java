package com.demo.usermanager.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
@ApiModel(description = "User registration response body")
public class RegisterUserResponse {

    @ApiModelProperty(notes = "Id of the created user", position = 1, example = "a03b83dc-55f9-4dfc-8d57-5521c5b75040")
    private String id;
    @ApiModelProperty(notes = "User creation date", position = 2, example = "Sun May 22 12:36:22 COT 2022")
    private String created;
    @ApiModelProperty(notes = "Last user update date", position = 3, example = "Sun May 22 12:36:22 COT 2022")
    private String modified;
    @ApiModelProperty(notes = "Last login date", position = 4, example = "Sun May 22 12:36:22 COT 2022")
    private String lastLogin;
    @ApiModelProperty(notes = "Token for created user", position = 5, example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdWFuQHJvZHJpZ3Vlei5vcmciLCJleHAiOjE2NTMyNzI1NzIsImlhdCI6MTY1MzIzNjU3Mn0.CIDmJe74I-Nu1i_PTXqttm1zfSqw4TldfQUW1XycdVClKB6Fuu-DsMiE8iSPyYXkG7iQvt47WFMFGs7OFZEYmg")
    private String token;
    @ApiModelProperty(notes = "User is active or not", position = 6, example = "true")
    private boolean isActive;

}
