package com.amirscode;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("library")
public class Main {

    private static CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository){
        Main.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);



    }


    @GetMapping
    public static List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
    record NewCustomerRequest(
        String name,
        String email,
        Integer age,
        String bookToBorrow
    ) {

    }
    @PostMapping()
    public ResponseEntity<String> addCustomer(@RequestBody NewCustomerRequest request){
        try {
            Customer customer = new Customer();
            customer.setName(request.name);
            customer.setEmail(request.email);
            customer.setAge(request.age);
            customer.setBookToBorrow(request.bookToBorrow);
            customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add customer.");
        }


    }



    @DeleteMapping("{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") Integer id){
        try {
            customerRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such customer.");
        }
    }

    @DeleteMapping("/deleteCustomerByParams")
    public ResponseEntity<String> deleteCustomerById(@RequestParam Integer id) {
        try {
            customerRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such customer.");
        }
    }

    @PutMapping("{customerId}")
    public ResponseEntity<String> updateCustomer(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request){
        return getStringResponseEntity(id, request);
    }

    private ResponseEntity<String> getStringResponseEntity(@PathVariable("customerId") Integer id, @RequestBody NewCustomerRequest request) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setName(request.name);
            customer.setEmail(request.email);
            customer.setAge(request.age);
            customer.setBookToBorrow(request.bookToBorrow);
            customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success!");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such customer.");
    }

    @PutMapping("/putByParams")
    public ResponseEntity<String> updateCustomerById(@RequestParam Integer id, @RequestBody NewCustomerRequest request) {
        return getStringResponseEntity(id, request);
    }



    @GetMapping("{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") Integer id){
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("getByParams")
    public ResponseEntity<Customer> getCustomerByCustomerId(@RequestParam Integer id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(customer);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }



}

//Merging






