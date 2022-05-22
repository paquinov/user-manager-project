package com.demo.usermanager.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorResponse {

    private String message;

}
