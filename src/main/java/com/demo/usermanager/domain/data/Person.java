package com.demo.usermanager.domain.data;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class Person {

    private String id;
    @NotBlank(message = "El nombre del usuario no puede estar vacio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String name;
    private Date createdDate;
    private Date lastUpdateDate;
    @Valid
    private List<Phone> phoneList;

}
