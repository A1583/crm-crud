package com.crm.crud.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer customerId;
    String firstName;
    String lastName;
    LocalDate customerDate;
    Boolean isVip;
    String statusCode;
    LocalDateTime createdOn;
    LocalDateTime modifiedOn;
}
