package com.demo.dao;

import com.demo.model.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by d.kudryashov on 13.07.2016.
 */
public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {

    public Customer findByFirstName(String firstName);

    public List<Customer> findByLastName(String lastName);

}
