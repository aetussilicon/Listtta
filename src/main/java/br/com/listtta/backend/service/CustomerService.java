package br.com.listtta.backend.service;

import br.com.listtta.backend.exceptions.UpdateFieldsException;
import br.com.listtta.backend.exceptions.UserNotFound;
import br.com.listtta.backend.model.dto.customer.CustomerDto;
import br.com.listtta.backend.model.dto.customer.CustomerSignUpDto;
import br.com.listtta.backend.model.dto.customer.CustomerUpdateDto;
import br.com.listtta.backend.model.entities.Customer;
import br.com.listtta.backend.model.mapper.CustomerMapper;
import br.com.listtta.backend.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final UsernameGenerateService usernameGenerateService;
    private final CustomerMapper customerMapper;

    public Customer createNewCustomer(CustomerSignUpDto customerSignUpDto) {
        customerSignUpDto.setUsername(
                usernameGenerateService.usernameGenerator(customerSignUpDto.getFullName())
        );

        return customerRepository.save(customerMapper.signUpDtoToModel(customerSignUpDto));
    }

    public Customer patchCustomer(UUID customerId, CustomerUpdateDto customerUpdateDto){
        Optional<Customer> checkCustomerInDatabase = customerRepository.findById(customerId);

        Customer customerToUpdate = checkCustomerInDatabase.get();
        Customer updateFields = customerMapper.updateDtoToModel(customerUpdateDto);

        if (checkCustomerInDatabase.isPresent()) {
            for (Field field : Customer.class.getDeclaredFields()) {
                field.setAccessible(true);

                try {
                    if (field.get(updateFields) != null && !field.get(updateFields).equals(field.get(customerToUpdate))) {
                        field.set(customerToUpdate, field.get(updateFields));
                    }
                } catch (IllegalAccessException e){
                    throw new UpdateFieldsException("Não foi possível atualizar o usuário!");
                }
            }
        }
        return customerToUpdate;
    }

    public CustomerDto getOneCustomer(UUID customerId) {
        Optional<Customer> checkCustomerInDatabase = customerRepository.findById(customerId);
        if (checkCustomerInDatabase.isPresent()) {
            return customerMapper.customerModelToDto(checkCustomerInDatabase.get());
        }
        throw new UserNotFound("Usuário não encontrado!");
    }

    public List<CustomerDto> getAllCustomers() {
        return customerMapper.listModelToDto(customerRepository.findAll());
    }

    public boolean deleteCustomer(UUID customerId) {
        Optional<Customer> checkInDatabase = customerRepository.findById(customerId);
        if (checkInDatabase.isPresent()) {
            customerRepository.deleteById(customerId);
            return true;
        } return false;
    }

}
