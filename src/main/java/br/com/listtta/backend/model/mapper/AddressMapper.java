package br.com.listtta.backend.model.mapper;

import br.com.listtta.backend.model.dto.address.NewUserAddressDto;
import br.com.listtta.backend.model.dto.address.UpdateUserAddressDto;
import br.com.listtta.backend.model.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(target = "addressId", source = "addressId")
    Address newUserAddressDtoToModel(NewUserAddressDto newUserAddressDto);

    @Mapping(target = "addressId", ignore = true)
    Address updateUserAddressDtoToModel(UpdateUserAddressDto updateUserAddressDto);
}
