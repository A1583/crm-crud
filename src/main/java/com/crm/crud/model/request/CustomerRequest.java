package com.crm.crud.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequest {
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @NotNull
    LocalDate customerDate;
    @NotNull
    Boolean isVip;
    @NotBlank
    String statusCode;
}
