package br.com.listtta.backend.controller;

import br.com.listtta.backend.model.dto.customer.CustomerDto;
import br.com.listtta.backend.model.dto.customer.CustomerSignUpDto;
import br.com.listtta.backend.model.dto.customer.CustomerUpdateDto;
import br.com.listtta.backend.model.entities.Customer;
import br.com.listtta.backend.service.CustomerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody @Valid CustomerSignUpDto customerSignUpDto) {
        return new ResponseEntity<>(customerService.createNewCustomer(customerSignUpDto), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{userId}")
    @Transactional
    public ResponseEntity<Customer> patchCustomer(@PathVariable UUID userId, @RequestBody CustomerUpdateDto customerUpdateDto) {
        return new ResponseEntity<>(customerService.patchCustomer(userId, customerUpdateDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/list/customer/{userId}")
    public ResponseEntity<CustomerDto> getOneCustomer(@PathVariable UUID userId) {
        return new ResponseEntity<>(customerService.getOneCustomer(userId), HttpStatus.OK);
    }

    @GetMapping("/list/all")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

}
