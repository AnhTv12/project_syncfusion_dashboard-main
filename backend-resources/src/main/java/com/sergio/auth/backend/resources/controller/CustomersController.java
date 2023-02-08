package com.sergio.auth.backend.resources.controller;
import com.sergio.auth.backend.resources.dto.CustomersDto;
import com.sergio.auth.backend.resources.model.Customers;
import com.sergio.auth.backend.resources.service.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
@Transactional
@CrossOrigin
public class CustomersController {

    private final CustomerService customerService;


    @GetMapping("/all")
    public ResponseEntity<List<CustomersDto>> listBranches(){
        List<Customers> customers = customerService.listCustomers();
        List<CustomersDto> customersDtoList = customers.stream().map(customerService::dtoMapper).toList();
        return ResponseEntity.ok().body(customersDtoList);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomersDto> findCustomer(@PathVariable("customerId") int customerId){
        Customers customer = customerService.findCustomer(customerId);
        return ResponseEntity.ok().body(customerService.dtoMapper(customer));
    }

    @PostMapping("/add")
    public ResponseEntity<CustomersDto> addBranch(@RequestBody CustomersDto customersDto){
        customerService.addCustomer(customerService.EntityMapper(customersDto));
        return ResponseEntity
                        .ok()
                        .body(customersDto);
    }

    @PostMapping("/edit")
    public ResponseEntity<CustomersDto> editBranch(@RequestBody CustomersDto customersDto){
        customerService.editCustomer(customerService.EntityMapper(customersDto));
        return ResponseEntity
                .accepted()
                .body(customersDto);
    }

    @GetMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("customerId") int id){
        return ResponseEntity
                .ok()
                .body(customerService.deleteCustomer(id));
    }

    @GetMapping("/delete/all")
    public ResponseEntity<String> deleteCustomers(){
        return ResponseEntity
                .ok()
                .body(customerService.deleteAllCustomers());
    }

    public CustomersController(CustomerService customerService) {
        this.customerService = customerService;
    }
}
