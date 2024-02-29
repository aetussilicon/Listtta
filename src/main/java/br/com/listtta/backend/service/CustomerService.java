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
    Customer checkedCustomer;

    //Method to check if the customer exists in the database with it's customerId.
    private Customer checkCustomerInDatabase(UUID userId) {
        Optional<Customer> checkCustomer = customerRepository.findById(userId);

        if (checkCustomer.isPresent()) {
            return checkCustomer.get();
        } throw new UserNotFound("Usuário não encontrado!");
    }

    public Customer createNewCustomer(CustomerSignUpDto customerSignUpDto) {
        customerSignUpDto.setUsername(usernameGenerateService.usernameGenerator(customerSignUpDto.getFullName()));
        return customerRepository.save(customerMapper.signUpDtoToModel(customerSignUpDto));
    }

    public Customer patchCustomer(UUID userId, CustomerUpdateDto customerUpdateDto) {
        checkedCustomer = checkCustomerInDatabase(userId);
        Customer updateFields = customerMapper.updateDtoToModel(customerUpdateDto);

            for (Field field : Customer.class.getSuperclass().getDeclaredFields()) {
                field.setAccessible(true);

                try {
                    if (field.get(updateFields) != null && !field.get(updateFields).equals(field.get(checkedCustomer))) {
                        field.set(checkedCustomer, field.get(updateFields));
                    }
                } catch (IllegalAccessException e) {
                    throw new UpdateFieldsException("Não foi possível atualizar o usuário!");
                }
            }
        return checkedCustomer;
    }

    public CustomerDto getOneCustomer(UUID userId) {
        return customerMapper.customerModelToDto(checkCustomerInDatabase(userId));
    }

    public List<CustomerDto> getAllCustomers() {
        return customerMapper.listModelToDto(customerRepository.findAll());
    }

    public String deleteCustomer(UUID userId) {
        checkCustomerInDatabase(userId);
        customerRepository.deleteById(userId);
        return "Usuário deletado com sucesso!";
    }
}