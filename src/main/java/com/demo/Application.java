package com.demo;

import com.demo.dao.CustomerRepository;
import com.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * Created by d.kudryashov on 13.07.2016.
 */
@SpringBootApplication
@ComponentScan("com.demo")
@EnableElasticsearchRepositories(basePackages = "com.demo.dao")
public class Application implements CommandLineRunner {

    @Autowired
    private CustomerRepository repository;

    @Override
    public void run(String... args) throws Exception {
        this.repository.deleteAll();
        saveCustomers();
        fetchAllCustomers();
        fetchIndividualCustomers();
    }

    private void saveCustomers() {
        this.repository.save(new Customer("Alice", "Smith"));
        this.repository.save(new Customer("Bob", "Smith"));
    }

    private void fetchAllCustomers() {
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : this.repository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();
    }

    private void fetchIndividualCustomers() {
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(this.repository.findByFirstName("Alice"));

        System.out.println("Customers found with findByLastName('Smith'):");
        System.out.println("--------------------------------");
        for (Customer customer : this.repository.findByLastName("Smith")) {
            System.out.println(customer);
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class).close();
    }
}
