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

    @PatchMapping("/update/{username}")
    @Transactional
    public ResponseEntity<Customer> patchCustomer(@PathVariable String username, @RequestBody CustomerUpdateDto customerUpdateDto) {
        return new ResponseEntity<>(customerService.patchCustomer(username, customerUpdateDto), HttpStatus.ACCEPTED);
    }

    @GetMapping("/list/{username}")
    public ResponseEntity<CustomerDto> getOneCustomer(@PathVariable String username) {
        return new ResponseEntity<>(customerService.getOneCustomer(username), HttpStatus.OK);
    }

    @GetMapping("/list/all")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @DeleteMapping("delete/{username}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String username) {
        return new ResponseEntity<>(customerService.deleteCustomer(username), HttpStatus.ACCEPTED);
    }

}
