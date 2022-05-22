package com.demo.usermanager.domain.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Builder
public class Phone {

    private String id;
    @Size(max = 9, message = "El campo number excede el limite permitido de 9")
    @NotBlank(message = "El campo number es obligatorio")
    private String number;
    @Size(max = 6, message = "El campo cityCode excede el limite permitido de 6")
    @NotBlank(message = "El campo cityCode es obligatorio")
    private String cityCode;
    @Size(max = 6, message = "El campo countryCode excede el limite permitido de 6")
    @NotBlank(message = "El campo countryCode es obligatorio")
    private String countryCode;
    private boolean isActive;
    private Date createdDate;

}
