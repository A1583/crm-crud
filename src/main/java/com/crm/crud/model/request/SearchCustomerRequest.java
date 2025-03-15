package com.crm.crud.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchCustomerRequest extends CustomerRequest {
    Integer customerId;
    LocalDateTime createdOn;
    LocalDateTime modifiedOn;
}
