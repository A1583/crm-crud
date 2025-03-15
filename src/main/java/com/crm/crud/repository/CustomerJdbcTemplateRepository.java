package com.crm.crud.repository;

import com.crm.crud.model.request.SearchCustomerRequest;
import com.crm.crud.repository.entity.Customer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomerJdbcTemplateRepository {
    NamedParameterJdbcTemplate jdbcTemplate;

    public List<Customer> search(SearchCustomerRequest request){
        var params = new MapSqlParameterSource();
        var sql = new StringBuilder("select * from customer where 1=1 ");

        if (Objects.nonNull(request.getCustomerId())) {
            sql.append(" and customer_id = :customerId");
            params.addValue("customerId", request.getCustomerId());
        }

        if (StringUtils.isNotBlank(request.getFirstName())) {
            sql.append(" and first_name = :firstName");
            params.addValue("firstName", request.getFirstName());
        }

        if (StringUtils.isNotBlank(request.getLastName())) {
            sql.append(" and last_name = :lastName");
            params.addValue("lastName", request.getLastName());
        }

        if (Objects.nonNull(request.getCustomerDate())){
            sql.append(" and customer_date = :customerDate");
            params.addValue("customerDate", request.getCustomerDate());
        }

        if (Objects.nonNull(request.getIsVip())) {
            sql.append(" and is_vip = :isVip");
            params.addValue("isVip", request.getIsVip());
        }

        if (StringUtils.isNotBlank(request.getStatusCode())) {
            sql.append(" and status_code = :statusCode");
            params.addValue("statusCode", request.getStatusCode());
        }

        if (Objects.nonNull(request.getCreatedOn())){
            sql.append(" and created_on = :createdOn");
            params.addValue("createdOn", request.getCreatedOn());
        }

        if (Objects.nonNull(request.getModifiedOn())){
            sql.append(" and modified_on = :modifiedOn");
            params.addValue("modifiedOn", request.getModifiedOn());
        }

        sql.append(" order by customer_id");

        return jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Customer.class));
    }
}
