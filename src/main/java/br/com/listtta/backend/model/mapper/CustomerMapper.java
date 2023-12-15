package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.customer.CustomerDto;
import br.com.listtta.backend.model.dto.customer.CustomerSignUpDto;
import br.com.listtta.backend.model.dto.customer.CustomerUpdateDto;
import br.com.listtta.backend.model.entities.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(target = "customerId", expression = "java(UUID.randomUUID())")
    Customer signUpDtoToModel (CustomerSignUpDto customerSignUpDto);

    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "taxNumber", ignore = true)
    Customer updateDtoToModel(CustomerUpdateDto customerUpdateDto);

    CustomerDto customerModelToDto(Customer customers);

    List<CustomerDto> listModelToDto (List<Customer> customerList);


}
