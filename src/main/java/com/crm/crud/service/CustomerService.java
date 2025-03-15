package com.crm.crud.service;

import com.crm.crud.model.request.CreateCustomerRequest;
import com.crm.crud.model.request.CustomerRequest;
import com.crm.crud.model.request.SearchCustomerRequest;
import com.crm.crud.repository.CustomerJdbcTemplateRepository;
import com.crm.crud.repository.CustomerRepository;
import com.crm.crud.repository.entity.Customer;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerJdbcTemplateRepository customerJdbcTemplateRepository;

    @Transactional
    public List<Customer> create(CreateCustomerRequest request) {
        return processCustomer(request.getCustomerRequests(), null);
    }

    private List<Customer> processCustomer(List<CustomerRequest> request, Integer customerId) {
        try {
            var customerList = new ArrayList<Customer>();
            var now = LocalDateTime.now();
            for (var input : request) {
                Customer customer;
                if (Objects.nonNull(customerId)) {
                    customer = findCustomerById(customerId);
                } else {
                    customer = Customer.builder().createdOn(now).build();
                }

                customer.setFirstName(input.getFirstName());
                customer.setLastName(input.getLastName());
                customer.setCustomerDate(input.getCustomerDate());
                customer.setIsVip(input.getIsVip());
                customer.setStatusCode(input.getStatusCode());
                customer.setModifiedOn(now);
                customer.setCreatedOn(now);
                customerList.add(customer);
            }

            return customerRepository.saveAll(customerList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    private Customer findCustomerById(Integer customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException(MessageFormat.format("CustomerId: {0} not found", customerId)));
    }

    @Transactional
    public Customer update(CustomerRequest request, Integer customerId) {
        return processCustomer(List.of(request), customerId).get(0);
    }

    @Transactional
    public void delete(Integer customerId) {
        var customer = findCustomerById(customerId);
        customerRepository.delete(customer);
        log.info(MessageFormat.format("CustomerId: {0} deleted", customerId));
    }

    public List<Customer> search(SearchCustomerRequest request) {
        return customerJdbcTemplateRepository.search(request);
    }
}
